package cn.mingyuan.rpc.example.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan( {"cn.mingyuan"})
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.test();
    }

}
