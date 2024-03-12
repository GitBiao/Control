package cn.mingyuan.rpc.service;

import cn.mingyuan.rpc.api.User;
import cn.mingyuan.rpc.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(String id) {
        return new User(id,"biao");
    }
}
