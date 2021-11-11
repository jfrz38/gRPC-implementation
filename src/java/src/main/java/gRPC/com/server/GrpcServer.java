package gRPC.com.server;

import java.io.IOException;

import gRPC.com.server.repository.UserRepositoryImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

	private Server server;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		final GrpcServer server = new GrpcServer();
		server.start();
		server.blockUntilShutdown();
	}

	private void start() throws IOException, InterruptedException {
		server = ServerBuilder.forPort(50051)
				.addService(new UserService(new UserRepositoryImpl()))
				.build().start();
		System.out.println("Server start at " + server.getPort());
		server.awaitTermination();

	}

	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}
}
