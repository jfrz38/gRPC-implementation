package gRPC.com.client;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.ByteString;

import gRPC.com.protos.*;
import gRPC.com.protos.UserServiceGrpc.UserServiceBlockingStub;
import gRPC.com.protos.UserServiceGrpc.UserServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcClient extends UserServiceGrpc.UserServiceImplBase {

	private static UserServiceBlockingStub stub;
	private static UserServiceStub asyncStub;

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		stub = UserServiceGrpc.newBlockingStub(channel);
		asyncStub = UserServiceGrpc.newStub(channel);
		// Get client 1
		User user = stub.getUser(Id.newBuilder().setId(1).build());
		System.out.println("User = " + user.getId() + " ; " + user.getName() + " ; " + user.getData().toStringUtf8());

		// User not found
		try {
			stub.getUser(Id.newBuilder().setId(10).build()); // Not found
		} catch (Exception exception) {
			System.out.println("exception = " + exception.getMessage());
		}

		// Create user
		stub.createUser(UserDTO.newBuilder().setName("Juan").build());

		// Get all users
		UsersList users = stub.getAllUsers(Empty.newBuilder().build());
		for (User u : users.getUsersList()) {
			System.out
					.println("User in list = " + u.getId() + " ; " + u.getName() + " ; " + u.getData().toStringUtf8());
		}

		// Add data
		try {
			addData(1, "String from the client");
		} catch (InterruptedException e) {
			System.out.println("Error while client streaming");
		}

		// Get data
		getData(1);

		// exchange data
		exchangeData(1, "String from exchange");
		user = stub.getUser(Id.newBuilder().setId(1).build());
		System.out.println("User = " + user.getId() + " (again) ; " + user.getName() + " ; " + user.getData().toStringUtf8());

	}

	private static void exchangeData(int id, String send) {

		final CountDownLatch finishLatch = new CountDownLatch(1);
		StreamObserver<DataRequest> requestObserver = asyncStub.exchangeData(new StreamObserver<DataResponse>() {

			String serverText = "";
			@Override
			public void onNext(DataResponse value) {
				serverText += value.getData().toStringUtf8();
			}

			@Override
			public void onError(Throwable t) {
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				finishLatch.countDown();
				System.out.println("Final data = "+serverText);
			}

		});

		try {
			for (char c : send.toCharArray()) {
				DataRequest request = DataRequest.newBuilder().setId(id)
						.setData(ByteString.copyFromUtf8(String.valueOf(c))).build();
				requestObserver.onNext(request);
				if (finishLatch.getCount() == 0) {
					// RPC completed or errored before we finished sending.
					// Sending further requests won't error, but they will just be thrown away.
					return;
				}
			}
			requestObserver.onCompleted();

			finishLatch.await(1, TimeUnit.MINUTES);
		} catch (RuntimeException | InterruptedException e) {
			requestObserver.onError(e);
		}

	}
	
	private static void getData(int id) {
		Id request = Id.newBuilder().setId(id).build();
		Iterator<DataResponse> response;
		response = stub.getData(request);
		String result = "";
		while (response.hasNext()) {
			DataResponse next = response.next();
			result += next.getData().toStringUtf8();
		}
		System.out.println("User " + id + " sends : " + result);

	}

	private static void addData(int id, String send) throws InterruptedException {
		final CountDownLatch finishLatch = new CountDownLatch(1);

		StreamObserver<Empty> responseObserver = new StreamObserver<Empty>() {

			@Override
			public void onNext(Empty value) {
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error sending client streaming");
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				finishLatch.countDown();
			}
		};

		StreamObserver<DataRequest> requestObserver = asyncStub.addData(responseObserver);
		for (char c : send.toCharArray()) {
			DataRequest request = DataRequest.newBuilder().setId(id).setData(ByteString.copyFromUtf8(String.valueOf(c)))
					.build();
			requestObserver.onNext(request);
			if (finishLatch.getCount() == 0) {
				// RPC completed or errored before we finished sending.
				// Sending further requests won't error, but they will just be thrown away.
				return;
			}
		}
		requestObserver.onCompleted();

		finishLatch.await(1, TimeUnit.MINUTES);

	}
}
