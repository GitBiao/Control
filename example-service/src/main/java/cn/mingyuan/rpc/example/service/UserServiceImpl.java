package cn.mingyuan.rpc.example.service;


import cn.mingyuan.rpc.core.annotation.RPCService;
import cn.mingyuan.rpc.example.api.User;
import cn.mingyuan.rpc.example.api.UserService;

@RPCService
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(String id) {
        return new User(id,"biao");
    }

    @Override
    public User getUserByName(String name) {
        return new User("12",name);
    }
}
