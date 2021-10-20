import sys, os
import grpc
sys.path.insert(0, '../proto')
import football_pb2_grpc
from football_pb2 import Player, Team, Empty, Id

print("Initializing client...")

channel = grpc.insecure_channel("localhost:50051")
stub = football_pb2_grpc.FootballServiceStub(channel)

def getPlayer(id):
    request = Id(id=id)
    try:
        response = stub.GetPlayer(request)
        print("Player = "+str(response))
    except grpc.RpcError as error:
        print("Error al obtener el jugador con ID "+str(id)+": "+str(error.code()))
def getTeam(id):
    request = Id(id=id)
    try:
        response = stub.GetTeam(request)
        print("Team = "+str(response))
    except Exception as error:
        print("Error al obtener el equipo con ID "+str(id)+":"+str(error.code()))

getPlayer(1)
getPlayer(2)
getTeam(1)
getPlayer(-10)
