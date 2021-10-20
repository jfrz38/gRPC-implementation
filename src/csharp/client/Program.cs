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
                Player player2 = client.GetPlayer(new Id {Id_ = 2});
                Console.WriteLine($"The player found is {player2.Id} ; {player2.Name} ; {player2.Lastname} ; {player2.Age} ; {player2.Team}.");
                Team team1 = client.GetTeam(new Id{Id_ = 1});
                Console.WriteLine($"The team found is {team1.Id} ; {team1.Name} ; {team1.City}");
                Player newPlayer = new Player{Id=10, Name="player10", Lastname="lastName10", Age=10, Team=2};
                client.AddPlayer(newPlayer);
                Player addedPlayer = client.GetPlayer(new Id{Id_=10});
                Console.WriteLine($"The player found is {addedPlayer.Id} ; {addedPlayer.Name} ; {addedPlayer.Lastname} ; {addedPlayer.Age} ; {addedPlayer.Team}.");
                PlayersList allPlayers = client.GetAllPlayers(new Empty());
                Console.WriteLine($"The players are: {allPlayers}");
                TeamList allTeams = client.GetAllTeams(new Empty());
                Console.WriteLine($"The teams are: {allTeams}");
                //Player playerNotFound = client.GetPlayer(new Id {Id_ = -1});
                Team teamNotFound = client.GetTeam(new Id{Id_ = -1});
            }
            catch(RpcException ex)
            {
                Console.WriteLine($"Error: {{Code: {ex.StatusCode}, Status: {ex.Status.Detail}}}");
            }
        }
    }
}
