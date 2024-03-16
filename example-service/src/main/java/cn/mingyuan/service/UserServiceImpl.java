package cn.mingyuan.service;


import cn.mingyuan.rpc.api.User;
import cn.mingyuan.rpc.api.UserService;

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
