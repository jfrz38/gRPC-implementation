using System;
using Grpc.Core;

namespace grpcClient
{
    class Program
    {
        const int Port = 50051;
        static void Main(string[] args)
        {
            try
            {
                Channel channel = new Channel("127.0.0.1:50051", ChannelCredentials.Insecure);
                var client = new FootballService.FootballServiceClient(channel);
                Player player1 = client.GetPlayer(new Id {Id_ = 1});
                Console.WriteLine($"The player found is {player1.Id} ; {player1.Name} ; {player1.Lastname} ; {player1.Age} ; {player1.Team}.");
                client.AddPlayer(new Player{Name = "new player name", Lastname = "new lastName"});
                Player playerFound = client.GetPlayer(new Id {Id_ = 4});
                Console.WriteLine($"The player found is {playerFound.Id} ; {playerFound.Name} ; {playerFound.Lastname} ; {playerFound.Age} ; {playerFound.Team}.");
                Player playerNotFound = client.GetPlayer(new Id {Id_ = -1});
            }
            catch(RpcException ex)
            {
                Console.WriteLine($"Code: {ex.StatusCode}");
            }
        }
    }
}
