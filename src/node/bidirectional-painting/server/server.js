var path = require('path');
const grpc = require("@grpc/grpc-js");
const PROTO_PATH = path.resolve(__dirname, '../transform.proto');
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

server.addService(newsProto.Transform.service, {
    
    transform:(call, callback) => {
        // Read
        call.on('data', function(data){
            const x = data.canvas.width - data.mouse.x
            const y = data.canvas.height - data.mouse.y
            let color = data.color
            if (color.indexOf('#') === 0) {
                color = color.slice(1);
            }
            color = (Number(`0x1${color}`) ^ 0xFFFFFF).toString(16).substr(1).toUpperCase()
            // Write
            call.write({x:x, y:y, color:"#"+color})
        })
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
