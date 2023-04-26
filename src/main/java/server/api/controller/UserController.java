package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.api.model.AppUser;
import server.api.service.UserService;


@RestController


public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/users/add")
//    @CrossOrigin("*")
    public String addUser(@RequestBody AppUser user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        userService.addUser(user);
        return "OK";
    }

    @GetMapping("/users/get/{id}")
//    @CrossOrigin("*")
    public Object getUserId(@PathVariable(name = "id") String id) {
        System.out.println(id);
        return userService.getUser();
    }

    @GetMapping("/users/get")
//    @CrossOrigin("http://localhost:5173/")
    public Object getUser() {
        return userService.getUser();
    }
}