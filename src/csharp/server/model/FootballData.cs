using System;
using System.Collections.Generic;
using Grpc.Core;

namespace grpcServer
{
    class FootballData
    {

        private static List<Player> players = new List<Player>(){
            new Player{Id=1, Name="name1", Lastname="lastName1", Age=1, Team=1},
            new Player{Id=2, Name="name2", Lastname="lastName2", Age=2, Team=1},
            new Player{Id=3, Name="name3", Lastname="lastName3", Age=3, Team=2}
        };

        private static List<Team> teams = new List<Team>(){
            new Team{Id=1, Name="team1", City="city1"},
            new Team{Id=2, Name="team2", City="city2"}
        };

        /* PLAYERS */
        public PlayersList GetAllPlayers()
        {
            return new PlayersList
            {
                Players = {players}
            };
        }
        public Player GetPlayer(Id request){
            var playerFound = players.Find(p => p.Id.Equals(request.Id_));
            if(playerFound == null){
                throw new RpcException(new Status(StatusCode.NotFound, $"Player {request.Id_} not found"));
            }
            return playerFound;
        }
        public Empty AddPlayer(Player request)
        {
            players.Add(request);
            return new Empty();
        }

        public Empty DeletePlayer(Id request)
        {
            players.RemoveAll(p => p.Id == request.Id_);
            return new Empty();
        }

        /* TEAMS */
        public TeamList GetAllTeams(){
            
            return new TeamList
            {
                Teams = {teams}
            };
        }
        public Team GetTeam(Id request){
            var teamFound = teams.Find(t => t.Id.Equals(request.Id_));
            if(teamFound == null){
                throw new RpcException(new Status(StatusCode.NotFound, $"Team {request.Id_} not found"));
            }
            return teamFound;
        }

        public Empty AddTeam(Team request)
        {
            teams.Add(request);
            return new Empty();
        }

        public Empty DeleteTeam(Id request)
        {
            teams.RemoveAll(p => p.Id == request.Id_);
            return new Empty();
        }
    }
}
