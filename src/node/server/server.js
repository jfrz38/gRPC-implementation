const grpc = require("@grpc/grpc-js");
const PROTO_PATH = "../football.proto";
var protoLoader = require("@grpc/proto-loader");

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

let players = [{ id: 1, name: "name1", lastname: "ln1", position: "p1", age: 1, team: { id: 1 } },
{ id: 2, name: "name2", lastname: "ln2", position: "p2", age: 2, team: { id: 2 } }]
let teams = [{ id: 1, city: "city1", name: "name1", players: [{ id: 1 }, { id: 2 }] }]

server.addService(newsProto.FootballService.service, {
    getAllPlayers: (_, callback) => {
        callback(null, { players });
    },
    addPlayer: (call, callback) => {
        let id = Math.max.apply(Math, players.map(function (o) { return o.id }))
        call.request.id = ++id
        players.push(call.request)
        callback(null, {})
    },
    deletePlayer: (call, callback) => {
        const id = call.request.id
        if (!players.find(f => f.id === id)) {
            var error = new Error();
            error.code = grpc.status.NOT_FOUND;
            callback(error, {})
        } else {
            players = players.filter(f => f.id !== id)
            callback(null, {})
        }
    },

    getAllTeams: (_, callback) => {
        callback(null, { teams })
    },
    addTeam: (call, callback) => {
        let id = Math.max.apply(Math, teams.map(function (o) { return o.id }))
        call.request.id = ++id
        teams.push(call.request)
        callback(null, {})
    },
    deleteTeam: (call, callback) => {
        const id = call.request.id
        if (!teams.find(f => f.id === id)) {
            var error = new Error();
            error.code = grpc.status.NOT_FOUND;
            callback(error, {})
        } else {
            teams = teams.filter(f => f.id !== id)
            callback(null, {})
        }
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
