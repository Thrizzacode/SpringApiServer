package server.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CmsUser", description = "後台管理員資料")
public class CmsUserController extends ApiController{
    @Autowired
    CmsUserService cmsUserService;

    @Operation(
            summary = "登入",
            description = "帶入帳號及密碼進行驗證登入",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "登入成功"
                    ),
            }
    )
    @PostMapping("/users/login")
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

    static class LoginSuccessInfo {
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

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    @GetMapping("/users/get")
    @Operation(
            summary = "取得使用者資料",
            description = "取得使用者資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "取得使用者資料"
                    ),
            }
    )
    public CmsUser getUser(@RequestBody CmsUser cmsUser) {
        return cmsUserService.getUser(cmsUser.getUsername());
    }


    @PostMapping("/users/signup")
    @Operation(
            summary = "註冊",
            description = "註冊",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "註冊成功"
                    ),
            }
    )
    public ResponseEntity<CmsUser> addUser(@RequestBody CmsUser cmsUser, HttpSession session) {
        CmsUser addUser = cmsUserService.addUser(cmsUser, session);
        if (addUser != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addUser.getId())
                    .toUri();
            return ResponseEntity.created(location).body(addUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}