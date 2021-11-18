from __future__ import print_function
import sys, os
import grpc
sys.path.insert(0, '../protos')
import user_pb2_grpc
from user_pb2 import User, UserDTO, Empty, Id, DataRequest, DataResponse

def getAllUsers(stub):
    request = Empty()
    try:
        response = stub.GetAllUsers(request)
        print("Users = "+str(response))
    except grpc.RpcError as error:
        print("Error al obtener los usuarios: "+str(error.code()))

def getUser(id, stub):
    request = Id(id=id)
    try:
        response = stub.GetUser(request)
        print("User = "+str(response))
    except grpc.RpcError as error:
        print("Error al obtener el user con ID "+str(id)+": "+str(error.code()))

def createUser(user, stub):
    request = UserDTO(name=user["name"])
    try:
        response = stub.CreateUser(request)
        print("Created user, response: "+str(response))
    except grpc.RpcError as error:
        print("Error al crear el user "+str(user)+": "+str(error.code()))

def readStream(id, stub):
    request = Id(id=id)
    try:
        response = stub.GetData(request)
        text = ''
        for r in response:
            text += r.data.decode('utf-8')
        print("Response = "+str(text))
    except grpc.RpcError as error:
        print("Error al leer streaming del server para el usuario "+str(id)+": "+str(error.code()))  

def writeStream(id, text, stub):
    try:
        iterator = iter([DataRequest(id=id, data=str.encode(x)) for x in text])
        response = stub.AddData(iterator)
        print("Response: "+str(response))
    except grpc.RpcError as error:
        print("Error al escribir streaming del client para el usuario "+str(id)+": "+str(error.code()))      
        
def exchangeStream(id, text, stub):
    try:
        # Send
        iterator = iter([DataRequest(id=id, data=str.encode(x)) for x in text])
        response = stub.ExchangeData(iterator)
        # Read
        txt = ''
        for r in response:
            txt += r.data.decode("utf-8") 
        print("Response: "+str(txt))
    except grpc.RpcError as error:
        print("Error con el streaming bidireccional: "+str(error))  

def run():
    channel = grpc.insecure_channel("localhost:50051")
    stub = user_pb2_grpc.UserServiceStub(channel)
    getAllUsers(stub)
    getUser(1, stub)
    getUser(-1, stub)
    createUser({"name":"Juan"}, stub)
    writeStream(1,"Text from the python client", stub)
    readStream(1, stub)
    exchangeStream(1, "exchange string", stub)
    getUser(1,stub)

if __name__ == '__main__':
    run()
