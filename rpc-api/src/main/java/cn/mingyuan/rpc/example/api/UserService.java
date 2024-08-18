package cn.mingyuan.rpc.example.api;

public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

}
