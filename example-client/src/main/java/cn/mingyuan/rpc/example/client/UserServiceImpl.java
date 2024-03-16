package cn.mingyuan.rpc.example.client;

import cn.mingyuan.rpc.example.api.User;
import cn.mingyuan.rpc.example.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(String id) {
        return new User();
    }

    @Override
    public User getUserByName(String name) {
        return new User();
    }
}
