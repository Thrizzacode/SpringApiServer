package server.api.model;

import server.api.converter.CmsUserAuthListConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cms_user")
public class CmsUser {
    @Id
    private String id;
    private String username;
    private String password;
    @Convert(converter = CmsUserAuthListConverter.class)
    private List<CmsUserAuth> identity;
    private String create_time;

    public CmsUser() {

    }

    @Override
    public String toString() {
        return "CmsUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
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

    public List<CmsUserAuth> getIdentity() {
        return identity;
    }

    public void setIdentity(List<CmsUserAuth> identity) {
        this.identity = identity;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
