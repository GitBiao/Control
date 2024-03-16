package cn.mingyuan.rpc.example.client;

import cn.mingyuan.rpc.core.ProxyUtil;
import cn.mingyuan.rpc.example.api.User;
import cn.mingyuan.rpc.example.api.UserService;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        UserService userService = ProxyUtil.getRemoteProxyInstance(UserService.class, new InetSocketAddress("localhost", 8888));
        User user = userService.getUserByName("13");
        System.out.println(user);
        TimeUnit.SECONDS.sleep(2);
    }

}
