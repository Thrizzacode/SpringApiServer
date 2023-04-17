package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.api.model.User;
import server.api.service.UserService;


@RestController


public class UserController {
    @Autowired
    UserService userService;

    //    @RequestMapping("/users/add")
    @PostMapping("/users/add")
    public String addUser(@RequestBody User user) {
        System.out.println(user.getUserName());
        userService.addUser(user);
        return "OK";
    }

    @GetMapping("/users/get/{id}")
    @CrossOrigin("http://localhost:5173/")
    public Object getUser(@PathVariable(name="id")  String id) {
        System.out.println(id);
        return userService.getUser();
    }
}