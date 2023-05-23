package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.model.CmsUser;
import server.api.service.UserService;


@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users/login")
    @CrossOrigin("*")
    public ResponseEntity<?> userLogin(@RequestBody CmsUser cmsUser) {
        System.out.println(cmsUser.getUsername());
        System.out.println(cmsUser.getPassword());
        if (userService.userLogin(cmsUser.getUsername(), cmsUser.getPassword())) {
            System.out.println("OK");
            LoginSuccessInfo successInfo = new LoginSuccessInfo();
            return ResponseEntity.ok(successInfo);
        } else {
            System.out.println("error");
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

    @GetMapping("/users/get")
    public CmsUser getUser(@RequestBody CmsUser cmsUser) {
        System.out.println(cmsUser.getUsername());
        System.out.println(userService.getUser(cmsUser.getUsername()));
        return userService.getUser(cmsUser.getUsername());
    }
}