package gRPC.com.server;

import com.google.protobuf.ByteString;

import gRPC.com.protos.*;
import gRPC.com.server.repository.UserRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class UserService extends UserServiceGrpc.UserServiceImplBase{

private UserRepository repository;
	
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	
	@Override
	public void getAllUsers(Empty request, StreamObserver<UsersList> responseObserver) {
		responseObserver.onNext(this.repository.getAll());
		responseObserver.onCompleted();
	}

	@Override
	public void getUser(Id request, StreamObserver<User> responseObserver) {
		User user = this.repository.getById(request);
		if(user == null) {
			responseObserver.onError(
					Status.NOT_FOUND.withDescription(
							"User with ID " + request.getId() + " not found")
					.asRuntimeException());
			return;
		}
		responseObserver.onNext(user);
		responseObserver.onCompleted();
	}

	@Override
	public void createUser(UserDTO request, StreamObserver<Empty> responseObserver) {
		responseObserver.onNext(this.repository.createUser(request));
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<DataRequest> addData(StreamObserver<Empty> responseObserver) {
		return new StreamObserver<DataRequest>() {
			String txt = "";
			int id = -1;
			@Override
			public void onNext(DataRequest value) {
				id = value.getId();
				txt += value.getData().toStringUtf8();
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error reading client streaming");
			}

			@Override
			public void onCompleted() {
				repository.saveData(id, txt);	
				responseObserver.onCompleted();
			}
			
		};
	}

	@Override
	public void getData(Id request, StreamObserver<DataResponse> responseObserver) {
		User user = this.repository.getById(request);
		if(user == null) {
			responseObserver.onError(
					Status.NOT_FOUND.withDescription(
							"User with ID " + request.getId() + " not found")
					.asRuntimeException());
			return;
		}
		for (char c : user.getData().toStringUtf8().toCharArray()) {
			DataResponse response = DataResponse.newBuilder()
					.setData(ByteString.copyFromUtf8(String.valueOf(c)))
					.build();
			responseObserver.onNext(response);
		}
		responseObserver.onCompleted();

	}

	@Override
	public StreamObserver<DataRequest> exchangeData(StreamObserver<DataResponse> responseObserver) {
		String send = "Message to send to the client";
		for (char c : send.toCharArray()) {
			DataResponse response = DataResponse.newBuilder()
					.setData(ByteString.copyFromUtf8(String.valueOf(c)))
					.build();
			responseObserver.onNext(response);
			
		}
		return new StreamObserver<DataRequest>() {
			String txt = "";
			int id = -1;
			@Override
			public void onNext(DataRequest value) {
				id = value.getId();
				txt += value.getData().toStringUtf8();				
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error while bidirectional streaming");				
			}

			@Override
			public void onCompleted() {
				System.out.println("Result: For ID "+id+" string is '"+txt+"'");	
				responseObserver.onCompleted();
			}
			
		};
		
	}

}
