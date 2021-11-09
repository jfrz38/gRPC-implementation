package gRPC.com.server;

import com.google.protobuf.ByteString;

import gRPC.com.protos.*;
import io.grpc.stub.StreamObserver;

public class UserService extends UserServiceGrpc.UserServiceImplBase{

	@Override
	public void getAllUsers(Empty request, StreamObserver<UsersList> responseObserver) {
		// TODO Auto-generated method stub
		super.getAllUsers(request, responseObserver);
	}

	@Override
	public void getUser(Id request, StreamObserver<User> responseObserver) {
		System.out.println("Getting user : "+request.getId());
		User user = User.newBuilder()
				.setName("nuevo nombre")
				.setId(request.getId())
				.setData(ByteString.copyFrom("holaaa".getBytes()))
				.build();
		responseObserver.onNext(user);
		responseObserver.onCompleted();
	}

	@Override
	public void createUser(UserDTO request, StreamObserver<Empty> responseObserver) {
		// TODO Auto-generated method stub
		super.createUser(request, responseObserver);
	}

	@Override
	public StreamObserver<DataRequest> addData(StreamObserver<Empty> responseObserver) {
		// TODO Auto-generated method stub
		return super.addData(responseObserver);
	}

	@Override
	public void getData(Id request, StreamObserver<DataResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getData(request, responseObserver);
	}

	@Override
	public StreamObserver<DataRequest> exchangeData(StreamObserver<DataResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.exchangeData(responseObserver);
	}

	
}
