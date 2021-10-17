import grpc
import football_pb2_grpc
from football_pb2 import Player, Team, Empty, Id

print("Initializing client...")

channel = grpc.insecure_channel("localhost:50051")
stub = football_pb2_grpc.FootballServiceStub(channel)

def getPlayer(id):
    request = Id(id=id)
    response = stub.GetPlayer(request)
    print("Player = "+str(response))
def getTeam(id):
    request = Id(id=id)
    response = stub.GetTeam(request)
    print("Team = "+str(response))

getPlayer(1)
getPlayer(2)

getTeam(1)
