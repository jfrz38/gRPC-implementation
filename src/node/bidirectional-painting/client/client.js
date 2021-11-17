var path = require('path');
const grpc = require("@grpc/grpc-js");
var protoLoader = require("@grpc/proto-loader");
const PROTO_PATH = path.resolve(__dirname, '../transform.proto');

const options = {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
};

var packageDefinition = protoLoader.loadSync(PROTO_PATH, options);

const TransformService = grpc.loadPackageDefinition(packageDefinition).Transform;

const client = new TransformService(
  "localhost:50051",
  grpc.credentials.createInsecure()
);

module.exports = client;
