package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.api.model.CmsUser;
import server.api.service.CmsUserService;

import javax.servlet.http.HttpSession;
import java.net.URI;


@RestController
@RequestMapping("/api")

public class CmsUserController {
    @Autowired
    CmsUserService cmsUserService;

    @PostMapping("/users/login")
    @CrossOrigin("*")
    public ResponseEntity<?> userLogin(@RequestBody CmsUser cmsUser) {
        System.out.println(cmsUser.getUsername());
        System.out.println(cmsUser.getPassword());
        if (cmsUserService.userLogin(cmsUser.getUsername(), cmsUser.getPassword())) {
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
        System.out.println(cmsUserService.getUser(cmsUser.getUsername()));
        return cmsUserService.getUser(cmsUser.getUsername());
    }


    @PostMapping("/users/signup")
    @CrossOrigin("*")
    public ResponseEntity<CmsUser> addUser(@RequestBody CmsUser cmsUser, HttpSession session) {
        CmsUser addUser = cmsUserService.addUser(cmsUser, session);
        if(addUser != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addUser.getId())
                    .toUri();
            return ResponseEntity.created(location).body(addUser);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}