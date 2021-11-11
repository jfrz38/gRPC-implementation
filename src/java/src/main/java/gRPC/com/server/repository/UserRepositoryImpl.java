package gRPC.com.server.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.protobuf.ByteString;

import gRPC.com.protos.Empty;
import gRPC.com.protos.Id;
import gRPC.com.protos.User;
import gRPC.com.protos.UserDTO;
import gRPC.com.protos.UsersList;

public class UserRepositoryImpl implements UserRepository {

	private List<User> users = Stream.of(
			User.newBuilder().setId(1).setName("Pepe").setData(ByteString.copyFromUtf8("character string for Pepe"))
					.build(),
			User.newBuilder().setId(2).setName("Paco").setData(ByteString.copyFromUtf8("character string for Paco"))
					.build(),
			User.newBuilder().setId(3).setName("Manolo").setData(ByteString.copyFromUtf8("character string for Manolo"))
					.build())
			.collect(Collectors.toCollection(ArrayList::new));

	@Override
	public UsersList getAll() {
		return UsersList.newBuilder().addAllUsers(users).build();
	}

	@Override
	public User getById(Id id) {
		return users.stream().filter(u -> u.getId() == id.getId())
				.findFirst()
				.orElse(null);
	}

	@Override
	public Empty createUser(UserDTO user) {

		int lastId = users.get(users.size() - 1).getId();
		users.add(User.newBuilder().setId(++lastId).setName(user.getName())
				.setData(ByteString.copyFromUtf8("character string for " + user.getName())).build());

		return Empty.newBuilder().build();
	}

	@Override
	public Empty saveData(int id, String data) {
		System.out.println("Guardando "+data+" para el ID: "+id);
		User user = users.stream().filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
		if(user != null) {
			user = User.newBuilder(user)
					.setData(ByteString.copyFromUtf8(data))
					.build();
		}
		return Empty.newBuilder().build();
	}

}
