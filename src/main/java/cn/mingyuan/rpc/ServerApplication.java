package cn.mingyuan.rpc;

import cn.mingyuan.rpc.core.Registry;
import cn.mingyuan.rpc.core.RegistryCenter;
import cn.mingyuan.rpc.api.UserService;
import cn.mingyuan.rpc.service.UserServiceImpl;

public class ServerApplication {

    private static final Registry registry = new RegistryCenter(8888);

    public static void main(String[] args) {
        registry.register(UserService.class, UserServiceImpl.class);
        new Thread(registry::start,"server").start();
    }




}