package cn.mingyuan.rpc.example.service;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan( {"cn.mingyuan"})
public class ServerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ServerApplication.class);
        SocketServer socketServer = applicationContext.getBean(SocketServer.class);
        new Thread(socketServer::start,"server").start();
    }




}
