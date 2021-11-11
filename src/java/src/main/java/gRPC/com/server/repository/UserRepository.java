package gRPC.com.server.repository;

import gRPC.com.protos.*;

public interface UserRepository {

	public UsersList getAll();
	
	public User getById(Id id);
	
	public Empty createUser(UserDTO user);
	
	public Empty saveData(int id, String data);
	
}
