package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.api.service.UserService;


@RestController


public class UserController {
    @Autowired
    UserService userService;

    //    @RequestMapping("/users/add")
//    @PostMapping("/users/add")
//    public String addUser() {
//        User tUser = new User();
//        tUser.setId("001");
//        tUser.setUserName("我是001");
//        userService.addUser(tUser);
//        return "OK";
//    }

    @GetMapping("/users/get")
    public Object getUser() {
        return userService.getUser();
    }
}