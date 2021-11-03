using System;
using System.Text;
using System.Threading.Tasks;
using Grpc.Core;
using System.Collections.Generic;
using Google.Protobuf;

namespace grpcServer
{
    public class GrpcServerImpl : UserService.UserServiceBase
    {

        private static UserRepository repository = new UserRepository();
        public override Task<UsersList> GetAllUsers(Empty request, ServerCallContext context)
        {
            return Task.FromResult(repository.GetAllUsers());
        }
        public override Task<User> GetUser(Id request, ServerCallContext context)
        {
            return Task.FromResult(repository.GetUser(request));
        }

        public override Task<Empty> CreateUser(UserDTO request, ServerCallContext context)
        {
            return Task.FromResult(repository.CreateUser(request));
        }

        public override async Task GetData(Id request, IServerStreamWriter<DataResponse> response,
            ServerCallContext context)
        {
            var user = repository.GetUser(request);
            foreach (char c in user.Data.ToStringUtf8())
            {
                if(context.CancellationToken.IsCancellationRequested)
                {
                    Console.WriteLine("Cancelled by the client");
                    return;
                }
                await response.WriteAsync(new DataResponse { Data = ByteString.CopyFromUtf8(c.ToString()) });
            }
        
        }

        public override async Task<Empty> AddData(IAsyncStreamReader<DataRequest> request, ServerCallContext context)
        {
            int id = 0;
            StringBuilder text = new StringBuilder();
            while(await request.MoveNext() && !context.CancellationToken.IsCancellationRequested)
            {
                id = request.Current.Id;
                text.Append(request.Current.Data.ToStringUtf8());
            }
            
            return repository.AddData(id, text.ToString());
        }

        public override async Task ExchangeData(IAsyncStreamReader<DataRequest> request, IServerStreamWriter<DataResponse> response, ServerCallContext context)
        {
            // Read
            StringBuilder data = new StringBuilder();
            int id = 0;
            while(await request.MoveNext() && !context.CancellationToken.IsCancellationRequested)
            {
                id = request.Current.Id;
                data.Append(request.Current.Data.ToStringUtf8());  
            }
            repository.AddData(id, data.ToString());

            // Write
            string send = "String from the server";
            foreach (char c in send)
            {
                await response.WriteAsync(new DataResponse { Data = ByteString.CopyFromUtf8(c.ToString()) });
            }
        }
    }
}
