package cn.mingyuan.rpc.provider.controller;

import cn.mingyuan.rpc.example.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/control/user")
public class UserController {

    @GetMapping("/getById")
    public User getUser(String id){
        return new User(id,"zhang");
    }
}
