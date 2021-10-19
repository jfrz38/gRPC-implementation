using System;
using Grpc.Core;

namespace grpcServer
{
    class Program
    {
        const int Port = 50051;
        public static void Main(string[] args){
            
            try
            {
                Server server = new Server
                {
                    Services = {FootballService.BindService(new GrpcServerImpl())},
                    Ports = { new ServerPort("localhost", Port, ServerCredentials.Insecure)}
                };
                server.Start();
                Console.WriteLine($"gRPC server listening in port {Port}");
                Console.WriteLine("Press any key to stop the server...");
                Console.ReadKey();
                server.ShutdownAsync().Wait();
            }catch(Exception e){

            }
        }
    }
}
