package cn.mingyuan.rpc.example.client;

import cn.mingyuan.rpc.core.ProxyUtil;
import cn.mingyuan.rpc.core.annotation.RPCScan;
import cn.mingyuan.rpc.example.api.User;
import cn.mingyuan.rpc.example.api.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@RPCScan(basePackage = {"cn.mingyuan"})
public class Application {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.test();
    }

}
