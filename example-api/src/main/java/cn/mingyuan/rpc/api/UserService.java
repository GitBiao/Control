package cn.mingyuan.rpc.api;

public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

}
