package server.api.controller;

import server.api.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController


public class UserController {
    @Autowired
    CmsUserService cmsUserService;

    //    @RequestMapping("/users/add")
//    @PostMapping("/users/add")
//    public String addUser() {
//        User tUser = new User();
//        tUser.setId("001");
//        tUser.setUserName("我是001");
//        cmsUserService.addUser(tUser);
//        return "OK";
//    }

    @GetMapping("/users/get")
    public Object getUser() {
        return cmsUserService.getUser();
    }
}