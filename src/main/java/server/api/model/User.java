package server.api.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String id;
    private String user_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }
}
