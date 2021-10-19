using System;
using System.Threading.Tasks;
using Grpc.Core;

namespace grpcServer
{
    public class GrpcServerImpl : FootballService.FootballServiceBase{
        public override Task<Player> GetPlayer(Id request, ServerCallContext context)
        {
            FootballData data = new FootballData();
            return Task.FromResult(data.GetPlayer(request));
        }
    }
}
