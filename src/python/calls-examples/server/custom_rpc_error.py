from grpc import RpcError

class CustomRpcError(RpcError):
    def __init__(self, details, code):
        self._code = code
        self._details = details

    def code(self):
        return self._code

    def details(self):
        return self._details
