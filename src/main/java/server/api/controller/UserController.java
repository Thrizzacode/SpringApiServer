package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.model.User;
import server.api.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/users/add")
//    @CrossOrigin("*")
    public String addUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        userService.addUser(user);
        return "OK";
    }

    //登入驗證
    @PostMapping("/users/login")
    @CrossOrigin("*")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getIdentity());
        System.out.println(user.getCreate_time());
        if (userService.userLogin(user)) {
            LoginSuccessInfo successInfo = new LoginSuccessInfo();
            return ResponseEntity.ok(successInfo);
        } else {
            LoginErrorInfo errorInfo = new LoginErrorInfo("Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorInfo);
        }
    }

    static class LoginSuccessInfo{
        private int status;
        private String message;

        public LoginSuccessInfo() {
            this.status = HttpStatus.OK.value();
            this.message = "Login success";
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
     static class LoginErrorInfo {
        private String error;

        public LoginErrorInfo(String error) {
            this.error = error;
        }

        public String getError() { return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

//    @GetMapping("/users/get/{id}")
////    @CrossOrigin("*")
//    public Object getUserId(@PathVariable(name = "id") String id) {
//        System.out.println(id);
//        return userService.getUser();
//    }

//    @GetMapping("/users/get")
////    @CrossOrigin("http://localhost:5173/")
//    public Object getUser() {
//        return userService.getUser();
//    }

    @GetMapping("/users/getAll")
    @CrossOrigin("*")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}