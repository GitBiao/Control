package cn.mingyuan.rpc.example.client;

import cn.mingyuan.rpc.core.annotation.RpcReference;
import cn.mingyuan.rpc.example.api.User;
import cn.mingyuan.rpc.example.api.UserService;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    @RpcReference()
    private UserService userService;


    public void test() throws InterruptedException {
        User hello = this.userService.getUserById("111");
        System.out.println(hello);
        for (int i = 0; i < 10; i++) {
            System.out.println(userService.getUserById(i+""));
        }
    }

}
