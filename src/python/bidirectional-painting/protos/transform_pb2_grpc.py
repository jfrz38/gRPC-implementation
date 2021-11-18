# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import transform_pb2 as transform__pb2


class TransformStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.Transform = channel.stream_stream(
                '/Transform/Transform',
                request_serializer=transform__pb2.Point.SerializeToString,
                response_deserializer=transform__pb2.PointResponse.FromString,
                )


class TransformServicer(object):
    """Missing associated documentation comment in .proto file."""

    def Transform(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_TransformServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'Transform': grpc.stream_stream_rpc_method_handler(
                    servicer.Transform,
                    request_deserializer=transform__pb2.Point.FromString,
                    response_serializer=transform__pb2.PointResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'Transform', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class Transform(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def Transform(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(request_iterator, target, '/Transform/Transform',
            transform__pb2.Point.SerializeToString,
            transform__pb2.PointResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
