using System;

namespace grpcServer
{
    class FootballData
    {
        public Player GetPlayer(Id request){
            return new Player {Name = "n1", Lastname = "ln1", Age = 0};
        }
    }
}
