package cn.mingyuan.rpc.client;

import cn.mingyuan.core.ProxyUtil;
import cn.mingyuan.rpc.api.User;
import cn.mingyuan.rpc.api.UserService;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        UserService userService = ProxyUtil.getRemoteProxyInstance(UserService.class, new InetSocketAddress("localhost", 8888));
        User user = userService.getUserById("12");
        System.out.println(user);
        TimeUnit.SECONDS.sleep(2);
    }

}
