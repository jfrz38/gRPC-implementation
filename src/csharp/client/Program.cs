using System;
using System.Threading.Tasks;
using Grpc.Core;
using Google.Protobuf;

namespace grpcClient
{
    class Program
    {
        const int Port = 50051;
        static async Task Main(string[] args)
        {
            try
            {
                Channel channel = new Channel("127.0.0.1:50051", ChannelCredentials.Insecure);
                var client = new UserService.UserServiceClient(channel);
                User user1 = client.GetUser(new Id{Id_ = 1});
                Console.WriteLine($"User found: {user1.Id} ; {user1.Name} ; {user1.Data.ToStringUtf8()}");
                User user2 = client.GetUser(new Id{Id_ = 2});
                Console.WriteLine($"User found: {user2.Id} ; {user2.Name} ; {user2.Data.ToStringUtf8()}");
                client.CreateUser(new UserDTO{Name = "Juan"});
                User user3 = client.GetUser(new Id{Id_ = 4});
                Console.WriteLine($"User found: {user3.Id} ; {user3.Name} ; {user3.Data.ToStringUtf8()}");
                await GetDataStreaming(client);
                await AddDataStreaming(client);
                await ExchangeStreaming(client);
                await channel.ShutdownAsync();


            }
            catch (RpcException ex)
            {
                Console.WriteLine($"Error: {{Code: {ex.StatusCode}, Status: {ex.Status.Detail}}}");
            }
        }

        private static async Task GetDataStreaming(UserService.UserServiceClient client)
        {
            AsyncServerStreamingCall<DataResponse> response = client.GetData(new Id { Id_ = 1 });
            while (await response.ResponseStream.MoveNext())
            {
                DataResponse current = response.ResponseStream.Current;
                Console.Write($"{current.Data.ToStringUtf8()}");
            }
            Console.WriteLine();

        }

        private static async Task AddDataStreaming(UserService.UserServiceClient client)
        {
            using (var call = client.AddData(new Metadata()))
            {
                var str = "text to send";
                foreach (var c in str)
                {
                    await call.RequestStream.WriteAsync(new DataRequest { Id = 1, Data = ByteString.CopyFromUtf8(c.ToString()) });
                }
                await call.RequestStream.CompleteAsync();
                var response = await call;
                Console.WriteLine($"Empty: {response}");
            }

        }

        private static async Task ExchangeStreaming(UserService.UserServiceClient client)
        {
            using AsyncDuplexStreamingCall<DataRequest, DataResponse> stream = client.ExchangeData(new Metadata());

            //Write
            var str = "exchange to send";
            foreach (var c in str)
            {
                await stream.RequestStream.WriteAsync(new DataRequest { Id = 1, Data = ByteString.CopyFromUtf8(c.ToString()) });
            }
            await stream.RequestStream.CompleteAsync();
            // Read
            while(await stream.ResponseStream.MoveNext())
            {
                DataResponse current = stream.ResponseStream.Current;
                Console.Write($"{current.Data.ToStringUtf8()}");
            }
        }

    }
}
