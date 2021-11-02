using System;
using Grpc.Core;
using Google.Protobuf;

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
                var client = new UserService.UserServiceClient(channel);
                /*User user1 = client.GetUser(new Id{Id_ = 1});
                Console.WriteLine($"User found: {user1.Id} ; {user1.Name} ; {user1.Data.ToStringUtf8()}");
                User user2 = client.GetUser(new Id{Id_ = 2});
                Console.WriteLine($"User found: {user2.Id} ; {user2.Name} ; {user2.Data.ToStringUtf8()}");
                client.CreateUser(new UserDTO{Name = "Juan"});
                User user3 = client.GetUser(new Id{Id_ = 4});
                Console.WriteLine($"User found: {user3.Id} ; {user3.Name} ; {user3.Data.ToStringUtf8()}");*/
                //GetDataStreaming(client);
                AddDataStreaming(client);





                /*Player player1 = client.GetPlayer(new Id {Id_ = 1});
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
                Team teamNotFound = client.GetTeam(new Id{Id_ = -1});*/
            }
            catch (RpcException ex)
            {
                Console.WriteLine($"Error: {{Code: {ex.StatusCode}, Status: {ex.Status.Detail}}}");
            }
        }

        private static async void GetDataStreaming(UserService.UserServiceClient client)
        {
                Console.WriteLine("ENTRA CallGetDataStreaming");
            
                AsyncServerStreamingCall<DataResponse> response = client.GetData(new Id { Id_ = 1 });
                Console.WriteLine("PASA");
                Console.WriteLine($"MoveNext() = {response.ResponseStream}");
                while (await response.ResponseStream.MoveNext())
                {
                    Console.WriteLine("ENTRA WHILE");
                    DataResponse current = response.ResponseStream.Current;
                    Console.WriteLine($"{current.Data.ToStringUtf8()}");
                }
                Console.WriteLine("FIN");
            

        }

        private static async void AddDataStreaming(UserService.UserServiceClient client){

            Console.WriteLine("Start AddData");
            using var call = client.AddData(new Metadata());
            var str = "text to send";
            foreach(var c in str)
            {
                await call.RequestStream.WriteAsync(new DataRequest{Id = 1, Data = ByteString.CopyFromUtf8(c.ToString())});
            }
            await call.RequestStream.CompleteAsync();
            var response = await call;
            Console.WriteLine($"Empty: {response}");
        }
    }
}
