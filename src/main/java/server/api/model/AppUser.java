package server.api.model;

import org.springframework.stereotype.Component;

@Component
public class AppUser {
    private String id;


    private String username;
    private  String password;
    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
