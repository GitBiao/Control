package cn.mingyuan;

import cn.mingyuan.core.Registry;
import cn.mingyuan.core.RegistryCenter;
import cn.mingyuan.rpc.api.UserService;
import cn.mingyuan.service.UserServiceImpl;


public class ServerApplication {

    private static final Registry registry = new RegistryCenter(8888);

    public static void main(String[] args) {
        registry.register(UserService.class, UserServiceImpl.class);
        new Thread(registry::start,"server").start();
    }




}
