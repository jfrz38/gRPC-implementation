import grpc
from concurrent import futures
import sys, os
sys.path.insert(0, '../protos')
import user_pb2_grpc
from user_pb2 import DataResponse
from db import Database

class UserService(user_pb2_grpc.UserServiceServicer):
    db = {}

    def __init__(self):
        self.db = Database()

    def GetAllUsers(self, request, context):
        return self.db.get_all_users()

    def GetUser(self, request, context):
        return self.db.get_user(request.id)

    def CreateUser(self, request, context):
        return self.db.create_user(request)

    def AddData(self, request, context):
        return self.db.add_data(request)
    
    def GetData(self, request, context):
        user = self.db.get_user(request.id)
        return iter([DataResponse(data=str.encode(x)) for x in user.data.decode('utf-8')])
    
    def ExchangeData(self, request, context):
        # Read
        self.db.add_data(request)
        # Send
        return iter([DataResponse(data=str.encode(x)) for x in "String from the server"])        

def server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    user_pb2_grpc.add_UserServiceServicer_to_server(UserService(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Server listening on 50051")
    server.wait_for_termination()

def __exit__(self, exc_type, exc_val, exc_tb):
        print("EXITTTT")
        
if __name__ == '__main__':
    server()


