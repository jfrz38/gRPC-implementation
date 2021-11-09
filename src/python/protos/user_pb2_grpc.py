# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import user_pb2 as user__pb2


class UserServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.GetAllUsers = channel.unary_unary(
                '/UserService/GetAllUsers',
                request_serializer=user__pb2.Empty.SerializeToString,
                response_deserializer=user__pb2.UsersList.FromString,
                )
        self.GetUser = channel.unary_unary(
                '/UserService/GetUser',
                request_serializer=user__pb2.Id.SerializeToString,
                response_deserializer=user__pb2.User.FromString,
                )
        self.CreateUser = channel.unary_unary(
                '/UserService/CreateUser',
                request_serializer=user__pb2.UserDTO.SerializeToString,
                response_deserializer=user__pb2.Empty.FromString,
                )
        self.AddData = channel.stream_unary(
                '/UserService/AddData',
                request_serializer=user__pb2.DataRequest.SerializeToString,
                response_deserializer=user__pb2.Empty.FromString,
                )
        self.GetData = channel.unary_stream(
                '/UserService/GetData',
                request_serializer=user__pb2.Id.SerializeToString,
                response_deserializer=user__pb2.DataResponse.FromString,
                )
        self.ExchangeData = channel.stream_stream(
                '/UserService/ExchangeData',
                request_serializer=user__pb2.DataRequest.SerializeToString,
                response_deserializer=user__pb2.DataResponse.FromString,
                )


class UserServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def GetAllUsers(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def GetUser(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def CreateUser(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def AddData(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def GetData(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def ExchangeData(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_UserServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'GetAllUsers': grpc.unary_unary_rpc_method_handler(
                    servicer.GetAllUsers,
                    request_deserializer=user__pb2.Empty.FromString,
                    response_serializer=user__pb2.UsersList.SerializeToString,
            ),
            'GetUser': grpc.unary_unary_rpc_method_handler(
                    servicer.GetUser,
                    request_deserializer=user__pb2.Id.FromString,
                    response_serializer=user__pb2.User.SerializeToString,
            ),
            'CreateUser': grpc.unary_unary_rpc_method_handler(
                    servicer.CreateUser,
                    request_deserializer=user__pb2.UserDTO.FromString,
                    response_serializer=user__pb2.Empty.SerializeToString,
            ),
            'AddData': grpc.stream_unary_rpc_method_handler(
                    servicer.AddData,
                    request_deserializer=user__pb2.DataRequest.FromString,
                    response_serializer=user__pb2.Empty.SerializeToString,
            ),
            'GetData': grpc.unary_stream_rpc_method_handler(
                    servicer.GetData,
                    request_deserializer=user__pb2.Id.FromString,
                    response_serializer=user__pb2.DataResponse.SerializeToString,
            ),
            'ExchangeData': grpc.stream_stream_rpc_method_handler(
                    servicer.ExchangeData,
                    request_deserializer=user__pb2.DataRequest.FromString,
                    response_serializer=user__pb2.DataResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'UserService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class UserService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def GetAllUsers(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/UserService/GetAllUsers',
            user__pb2.Empty.SerializeToString,
            user__pb2.UsersList.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def GetUser(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/UserService/GetUser',
            user__pb2.Id.SerializeToString,
            user__pb2.User.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def CreateUser(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/UserService/CreateUser',
            user__pb2.UserDTO.SerializeToString,
            user__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def AddData(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_unary(request_iterator, target, '/UserService/AddData',
            user__pb2.DataRequest.SerializeToString,
            user__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def GetData(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/UserService/GetData',
            user__pb2.Id.SerializeToString,
            user__pb2.DataResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def ExchangeData(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(request_iterator, target, '/UserService/ExchangeData',
            user__pb2.DataRequest.SerializeToString,
            user__pb2.DataResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)