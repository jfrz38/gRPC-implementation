// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package gRPC.com.protos;

public interface UsersListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UsersList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .User users = 1;</code>
   */
  java.util.List<gRPC.com.protos.User> 
      getUsersList();
  /**
   * <code>repeated .User users = 1;</code>
   */
  gRPC.com.protos.User getUsers(int index);
  /**
   * <code>repeated .User users = 1;</code>
   */
  int getUsersCount();
  /**
   * <code>repeated .User users = 1;</code>
   */
  java.util.List<? extends gRPC.com.protos.UserOrBuilder> 
      getUsersOrBuilderList();
  /**
   * <code>repeated .User users = 1;</code>
   */
  gRPC.com.protos.UserOrBuilder getUsersOrBuilder(
      int index);
}
