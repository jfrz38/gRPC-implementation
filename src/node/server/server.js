const db = require('./db')
const grpc = require("@grpc/grpc-js");
const PROTO_PATH = "../football.proto";
var protoLoader = require("@grpc/proto-loader");
process.stdin.resume();
var server = new grpc.Server()
db.connect()
const options = {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true,
};
var packageDefinition = protoLoader.loadSync(PROTO_PATH, options);
const newsProto = grpc.loadPackageDefinition(packageDefinition);

server.addService(newsProto.FootballService.service, {
    getAllPlayers: (_, callback) => {
        db.getPlayers().then(players => {
            callback(null, players)
        })
    },
    getPlayer:(call, callback) => {
        db.getPlayer(call.request.id).then(player => {
            const args = player === undefined ? generateError(grpc.status.NOT_FOUND) : null
            callback(args, player)
        })
    },
    addPlayer: (call, callback) => {
        db.addPlayer(call.request).then((error, response) => {
            const args = error === undefined ? null : generateError(grpc.status.NOT_FOUND)
            callback(args, null)
        })
    },
    deletePlayer: (call, callback) => {
        db.deletePlayer(call.request.id).then((error, response) => {
            const args = error === undefined ? null : generateError(grpc.status.NOT_FOUND)
            callback(args, null)
        })
    },

    getAllTeams: (_, callback) => {
        db.getTeams().then(teams => {
            callback(null, teams)
        })
    },

    getTeam:(call, callback) => {
        db.getTeam(call.request.id).then(team => {
            const args = team === undefined ? generateError(grpc.status.NOT_FOUND) : null
            callback(args, team)
        })
    },
    addTeam: (call, callback) => {
        db.addTeam(call.request).then((error, response) => {
            const args = error === undefined ? null : generateError(grpc.status.NOT_FOUND)
            callback(args, null)
        })
    },
    deleteTeam: (call, callback) => {
        db.deleteTeam(call.request.id).then((error, response) => {
            const args = error === undefined ? null : generateError(grpc.status.NOT_FOUND)
            callback(args, null)
        })
    },
});

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

module.exports.initializeServer = () => {
    
}
