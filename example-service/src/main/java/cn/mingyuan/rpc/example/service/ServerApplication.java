package cn.mingyuan.rpc.example.service;


import cn.mingyuan.rpc.core.annotation.RPCScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RPCScan(basePackage = {"cn.mingyuan"})
public class ServerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ServerApplication.class);
        SocketServer socketServer = applicationContext.getBean(SocketServer.class);
        new Thread(socketServer::start,"server").start();
    }




}
