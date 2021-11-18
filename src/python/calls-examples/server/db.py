from grpc import StatusCode
from pymongo_inmemory import MongoClient # TODO
from user_pb2 import User, UsersList, Empty
from custom_rpc_error import CustomRpcError

class Database:
    users = [
            {"id": 1, "name": "Pepe", "data": str.encode("character string for Pepe")},
            {"id": 2, "name": "Paco", "data": str.encode("character string for Paco")},
            {"id": 3, "name": "Manolo", "data": str.encode("character string for Manolo")}]

    def get_all_users(self):
        return UsersList(users = self.users)
    
    def get_user(self, id):
        result = next((user for user in self.users if user['id'] == id), None)
        if(result == None):
            raise CustomRpcError("User not found", StatusCode.NOT_FOUND)
        return User(id = result["id"], name = result["name"], data = result["data"])
    
    def create_user(self, user):
        id = 1
        if(len(self.users) != 0):
            id = max(self.users, key=lambda item:item["id"])["id"] + 1
        self.users.append({"id":id, "name":user.name, "data":str.encode("character string for "+str(user.name))})
        return Empty()

    def add_data(self, request):
        id = 0
        text = ''
        for r in request:
            id = r.id
            text += r.data.decode("utf-8")
        for user in self.users:
            if(user["id"]==id): user["data"]=str.encode(text)
        return Empty()
    
        

