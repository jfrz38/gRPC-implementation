const grpc = require("@grpc/grpc-js");
var protoLoader = require("@grpc/proto-loader");
const PROTO_PATH = "./football.proto";

const options = {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
};

var packageDefinition = protoLoader.loadSync(PROTO_PATH, options);

const FootballService = grpc.loadPackageDefinition(packageDefinition).FootballService;

const client = new FootballService(
  "localhost:50051",
  grpc.credentials.createInsecure()
);

module.exports = client;
