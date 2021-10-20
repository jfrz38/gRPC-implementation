using System;
using Grpc.Core;

namespace grpcServer
{
    class Program
    {
        const int Port = 50051;
        public static void Main(string[] args){
            //https://medium.com/@nikhilajayk/creating-your-first-grpc-net-core-client-and-server-app-using-visual-studio-or-visual-studio-code-293a6a5a5f7
            //https://docs.microsoft.com/es-es/aspnet/core/grpc/basics?view=aspnetcore-5.0
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
                Console.WriteLine($"Exception = {e}");
            }
        }
    }
}
