package server.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import server.api.converter.CmsUserAuthListConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cms_user")
@EntityListeners(AuditingEntityListener.class)
public class CmsUser {
    @Id
    @GeneratedValue(generator = "user_id")
    @GenericGenerator(name = "user_id", strategy = "uuid")
    private String id;
    private String username;
    private String password;
    @Convert(converter = CmsUserAuthListConverter.class)
    private List<CmsUserAuth> identity;
    @CreatedDate
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
