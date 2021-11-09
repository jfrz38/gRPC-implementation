package gRPC.com.protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: example.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.Empty,
      gRPC.com.protos.UsersList> getGetAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllUsers",
      requestType = gRPC.com.protos.Empty.class,
      responseType = gRPC.com.protos.UsersList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.Empty,
      gRPC.com.protos.UsersList> getGetAllUsersMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.Empty, gRPC.com.protos.UsersList> getGetAllUsersMethod;
    if ((getGetAllUsersMethod = UserServiceGrpc.getGetAllUsersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetAllUsersMethod = UserServiceGrpc.getGetAllUsersMethod) == null) {
          UserServiceGrpc.getGetAllUsersMethod = getGetAllUsersMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.Empty, gRPC.com.protos.UsersList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "GetAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.UsersList.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetAllUsers"))
                  .build();
          }
        }
     }
     return getGetAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.Id,
      gRPC.com.protos.User> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUser",
      requestType = gRPC.com.protos.Id.class,
      responseType = gRPC.com.protos.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.Id,
      gRPC.com.protos.User> getGetUserMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.Id, gRPC.com.protos.User> getGetUserMethod;
    if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
          UserServiceGrpc.getGetUserMethod = getGetUserMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.Id, gRPC.com.protos.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "GetUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.Id.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.User.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetUser"))
                  .build();
          }
        }
     }
     return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.UserDTO,
      gRPC.com.protos.Empty> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = gRPC.com.protos.UserDTO.class,
      responseType = gRPC.com.protos.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.UserDTO,
      gRPC.com.protos.Empty> getCreateUserMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.UserDTO, gRPC.com.protos.Empty> getCreateUserMethod;
    if ((getCreateUserMethod = UserServiceGrpc.getCreateUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getCreateUserMethod = UserServiceGrpc.getCreateUserMethod) == null) {
          UserServiceGrpc.getCreateUserMethod = getCreateUserMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.UserDTO, gRPC.com.protos.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.UserDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("CreateUser"))
                  .build();
          }
        }
     }
     return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest,
      gRPC.com.protos.Empty> getAddDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddData",
      requestType = gRPC.com.protos.DataRequest.class,
      responseType = gRPC.com.protos.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest,
      gRPC.com.protos.Empty> getAddDataMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest, gRPC.com.protos.Empty> getAddDataMethod;
    if ((getAddDataMethod = UserServiceGrpc.getAddDataMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getAddDataMethod = UserServiceGrpc.getAddDataMethod) == null) {
          UserServiceGrpc.getAddDataMethod = getAddDataMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.DataRequest, gRPC.com.protos.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "AddData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.DataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("AddData"))
                  .build();
          }
        }
     }
     return getAddDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.Id,
      gRPC.com.protos.DataResponse> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetData",
      requestType = gRPC.com.protos.Id.class,
      responseType = gRPC.com.protos.DataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.Id,
      gRPC.com.protos.DataResponse> getGetDataMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.Id, gRPC.com.protos.DataResponse> getGetDataMethod;
    if ((getGetDataMethod = UserServiceGrpc.getGetDataMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetDataMethod = UserServiceGrpc.getGetDataMethod) == null) {
          UserServiceGrpc.getGetDataMethod = getGetDataMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.Id, gRPC.com.protos.DataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "GetData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.Id.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.DataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetData"))
                  .build();
          }
        }
     }
     return getGetDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest,
      gRPC.com.protos.DataResponse> getExchangeDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeData",
      requestType = gRPC.com.protos.DataRequest.class,
      responseType = gRPC.com.protos.DataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest,
      gRPC.com.protos.DataResponse> getExchangeDataMethod() {
    io.grpc.MethodDescriptor<gRPC.com.protos.DataRequest, gRPC.com.protos.DataResponse> getExchangeDataMethod;
    if ((getExchangeDataMethod = UserServiceGrpc.getExchangeDataMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getExchangeDataMethod = UserServiceGrpc.getExchangeDataMethod) == null) {
          UserServiceGrpc.getExchangeDataMethod = getExchangeDataMethod = 
              io.grpc.MethodDescriptor.<gRPC.com.protos.DataRequest, gRPC.com.protos.DataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "UserService", "ExchangeData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.DataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gRPC.com.protos.DataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("ExchangeData"))
                  .build();
          }
        }
     }
     return getExchangeDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAllUsers(gRPC.com.protos.Empty request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.UsersList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllUsersMethod(), responseObserver);
    }

    /**
     */
    public void getUser(gRPC.com.protos.Id request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.User> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    public void createUser(gRPC.com.protos.UserDTO request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<gRPC.com.protos.DataRequest> addData(
        io.grpc.stub.StreamObserver<gRPC.com.protos.Empty> responseObserver) {
      return asyncUnimplementedStreamingCall(getAddDataMethod(), responseObserver);
    }

    /**
     */
    public void getData(gRPC.com.protos.Id request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<gRPC.com.protos.DataRequest> exchangeData(
        io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getExchangeDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAllUsersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gRPC.com.protos.Empty,
                gRPC.com.protos.UsersList>(
                  this, METHODID_GET_ALL_USERS)))
          .addMethod(
            getGetUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gRPC.com.protos.Id,
                gRPC.com.protos.User>(
                  this, METHODID_GET_USER)))
          .addMethod(
            getCreateUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gRPC.com.protos.UserDTO,
                gRPC.com.protos.Empty>(
                  this, METHODID_CREATE_USER)))
          .addMethod(
            getAddDataMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                gRPC.com.protos.DataRequest,
                gRPC.com.protos.Empty>(
                  this, METHODID_ADD_DATA)))
          .addMethod(
            getGetDataMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                gRPC.com.protos.Id,
                gRPC.com.protos.DataResponse>(
                  this, METHODID_GET_DATA)))
          .addMethod(
            getExchangeDataMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                gRPC.com.protos.DataRequest,
                gRPC.com.protos.DataResponse>(
                  this, METHODID_EXCHANGE_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAllUsers(gRPC.com.protos.Empty request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.UsersList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUser(gRPC.com.protos.Id request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.User> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createUser(gRPC.com.protos.UserDTO request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<gRPC.com.protos.DataRequest> addData(
        io.grpc.stub.StreamObserver<gRPC.com.protos.Empty> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAddDataMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void getData(gRPC.com.protos.Id request,
        io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<gRPC.com.protos.DataRequest> exchangeData(
        io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getExchangeDataMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public gRPC.com.protos.UsersList getAllUsers(gRPC.com.protos.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public gRPC.com.protos.User getUser(gRPC.com.protos.Id request) {
      return blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public gRPC.com.protos.Empty createUser(gRPC.com.protos.UserDTO request) {
      return blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<gRPC.com.protos.DataResponse> getData(
        gRPC.com.protos.Id request) {
      return blockingServerStreamingCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gRPC.com.protos.UsersList> getAllUsers(
        gRPC.com.protos.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gRPC.com.protos.User> getUser(
        gRPC.com.protos.Id request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gRPC.com.protos.Empty> createUser(
        gRPC.com.protos.UserDTO request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_USERS = 0;
  private static final int METHODID_GET_USER = 1;
  private static final int METHODID_CREATE_USER = 2;
  private static final int METHODID_GET_DATA = 3;
  private static final int METHODID_ADD_DATA = 4;
  private static final int METHODID_EXCHANGE_DATA = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_USERS:
          serviceImpl.getAllUsers((gRPC.com.protos.Empty) request,
              (io.grpc.stub.StreamObserver<gRPC.com.protos.UsersList>) responseObserver);
          break;
        case METHODID_GET_USER:
          serviceImpl.getUser((gRPC.com.protos.Id) request,
              (io.grpc.stub.StreamObserver<gRPC.com.protos.User>) responseObserver);
          break;
        case METHODID_CREATE_USER:
          serviceImpl.createUser((gRPC.com.protos.UserDTO) request,
              (io.grpc.stub.StreamObserver<gRPC.com.protos.Empty>) responseObserver);
          break;
        case METHODID_GET_DATA:
          serviceImpl.getData((gRPC.com.protos.Id) request,
              (io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.addData(
              (io.grpc.stub.StreamObserver<gRPC.com.protos.Empty>) responseObserver);
        case METHODID_EXCHANGE_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.exchangeData(
              (io.grpc.stub.StreamObserver<gRPC.com.protos.DataResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gRPC.com.protos.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getGetAllUsersMethod())
              .addMethod(getGetUserMethod())
              .addMethod(getCreateUserMethod())
              .addMethod(getAddDataMethod())
              .addMethod(getGetDataMethod())
              .addMethod(getExchangeDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
