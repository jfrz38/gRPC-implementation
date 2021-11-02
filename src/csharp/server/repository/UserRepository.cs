using System;
using System.Collections.Generic;
using System.Linq;
using Grpc.Core;
using Google.Protobuf;

namespace grpcServer
{
    public class UserRepository
    {

        private static List<User> users = new List<User>(){
            new User{Id = 1, Name = "Pepe", Data = ByteString.CopyFromUtf8("character string for Pepe")},
            new User{Id = 2, Name = "Paco", Data = ByteString.CopyFromUtf8("character string for Paco")},
            new User{Id = 3, Name = "Manolo", Data = ByteString.CopyFromUtf8("character string for Manolo")}
        };

        public UsersList GetAllUsers(){
            return new UsersList
            {
                Users = {users}
            };
        }

        public User GetUser(Id request){
            var userFound = users.Find(user => user.Id == request.Id_);
            if(userFound == null){
                throw new RpcException(new Status(StatusCode.NotFound, $"User {request.Id_} not found"));
            }
            return userFound;
        }
        public Empty CreateUser(UserDTO request)
        {
            User lastUser = users.OrderBy(u => u.Id).LastOrDefault();
            int id = lastUser == null ? 1 : lastUser.Id + 1;
            users.Add(new User{Id = id, Name = request.Name, Data = ByteString.CopyFromUtf8("")});
            return new Empty();
        }

        public Empty AddData(int id, string text)
        {
            var user = users.Where(u => u.Id == id).FirstOrDefault();
            if(user != null)
            {
                user.Data = ByteString.CopyFromUtf8(text);
            }
            return new Empty();
        }
        
    }
}
