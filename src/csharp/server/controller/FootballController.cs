using System;
using System.Threading.Tasks;
using Grpc.Core;
using System.Collections.Generic;

namespace grpcServer
{
    public class GrpcServerImpl : FootballService.FootballServiceBase{

        private static FootballData data = new FootballData();

        /* PLAYERS */
        public override Task<PlayersList> GetAllPlayers(Empty request, ServerCallContext context)
        {
            return Task.FromResult(data.GetAllPlayers());
        }
        public override Task<Player> GetPlayer(Id request, ServerCallContext context)
        {
            return Task.FromResult(data.GetPlayer(request));
        }

        public override Task<Empty> AddPlayer(Player request, ServerCallContext context)
        {
            return Task.FromResult(data.AddPlayer(request));
        }

        public override Task<Empty> DeletePlayer(Id request, ServerCallContext context)
        {
            return Task.FromResult(data.DeletePlayer(request));
        }

        /* TEAMS */
        public override Task<TeamList> GetAllTeams(Empty request, ServerCallContext context)
        {
            return Task.FromResult(data.GetAllTeams());
        }
        public override Task<Team> GetTeam(Id request, ServerCallContext context)
        {
            return Task.FromResult(data.GetTeam(request));
        }

        public override Task<Empty> AddTeam(Team request, ServerCallContext context)
        {
            return Task.FromResult(data.AddTeam(request));
        }

        public override Task<Empty> DeleteTeam(Id request, ServerCallContext context)
        {
            return Task.FromResult(data.DeleteTeam(request));
        }
        
    }
}
