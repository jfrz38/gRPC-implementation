from logging import error
import grpc
from concurrent import futures
import sys, os
sys.path.insert(0, '../protos')
import transform_pb2_grpc
from transform_pb2 import PointResponse
from colors import hex, rgb

class Transform(transform_pb2_grpc.TransformServicer):
    
    def Transform(self, request, context):
        try:
            for r in request:
                x = r.canvas.width - r.mouse.x
                y = r.canvas.height - r.mouse.y
                hex = r.color
                if hex[0] == '#':
                    hex = hex[1:]
                rgb = (hex[0:2], hex[2:4], hex[4:6])
                color = "#"+"".join(['%02X' % (255 - int(a, 16)) for a in rgb])
                return iter([PointResponse(x=x, y=y, color=color)])
        except Exception as e:
            print("Error on the server side: "+str(e))

def server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    transform_pb2_grpc.add_TransformServicer_to_server(Transform(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Server listening on 50051")
    server.wait_for_termination()
        
if __name__ == '__main__':
    server()


