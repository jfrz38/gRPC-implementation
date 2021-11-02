const db = require('./db')
const grpc = require("@grpc/grpc-js");
const PROTO_PATH = "../user.proto";
var protoLoader = require("@grpc/proto-loader");
process.stdin.resume();

const options = {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true,
};
var packageDefinition = protoLoader.loadSync(PROTO_PATH, options);
const newsProto = grpc.loadPackageDefinition(packageDefinition);

const server = new grpc.Server();
db.connect()

server.addService(newsProto.UserService.service, {
    getAllUsers:(_, callback) => {
        db.getAllUsers().then(users => {
            const usersBuffer = users.map(usr => { return {id:usr.id, name:usr.name, data: Buffer.from(usr.data ?? "", 'utf8')}})
            callback(null, {users:usersBuffer})
        })
    },
    getUser:(call, callback) => {
        db.getUser(call.request.id).then(user => {
            callback(null, {id:user.id, name:user.name, data: Buffer.from(user.data, 'utf8')})
        })
    },
    createUser:(call, callback) => {
        db.createUser(call.request.name).then(_ => {
            callback(null, {})
        })
    },
    addData:(call, callback) => {
        let array = []
        let id
        call.on('data', function(data){
            id = data.id
            array.push(data.data.toString())
        })
        call.on('end', function(){
            db.addData(id, array.join("")).then(_ => {
                callback(null, {})
            })
        })
    },
    getData:(call, callback) => {
        db.getData(call.request.id).then(data => {
            for(const c of data.data){
                console.log("writing: ",c)
                call.write({data:Buffer.from(c, 'utf8')})
            }
            call.end()
        })
    },
    exchangeData:(call, callback) => {
        // Read
        let str = ''
        let id
        call.on('data', function(data){
            id = data.id
            str = str.concat(data.data.toString())
        })
        call.on('end', function(){
            db.addData(id, str)
        })

        // Write
        const response = "String from the server"
        for(const c of response){
            call.write({data:Buffer.from(c, 'utf8')})
        }
        call.end()
    }
})

server.bindAsync(
    "127.0.0.1:50051",
    grpc.ServerCredentials.createInsecure(),
    (error, port) => {
        console.log(`Server running at http://127.0.0.1:${port}`);
        server.start();
    }
);

function generateError(status) {
    let error = new Error()
    error.code = status
    return error
}
