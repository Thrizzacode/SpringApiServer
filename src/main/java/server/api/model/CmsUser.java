package server.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import server.api.converter.CmsUserAuthListConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cms_user")
@EntityListeners(AuditingEntityListener.class)
public class CmsUser {
    @Id
    @GeneratedValue(generator = "user_id")
    @GenericGenerator(name = "user_id", strategy = "uuid")
    @Schema(description = "管理員ID")
    private String id;
    @Schema(description = "管理員帳號")
    private String username;
    @Schema(description = "管理員密碼")
    private String password;
    @Email
    @Schema(description = "管理員信箱")
    private String email;
    @Convert(converter = CmsUserAuthListConverter.class)
    @Schema(description = "管理員權限")
    private List<CmsUserAuth> identity;
    @CreatedDate
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "建立時間")
    private Date create_time;
    @Transient
    @Schema(description = "驗證碼")
    private String verifyCode;

    public CmsUser() {

    }

    @Override
    public String toString() {
        return "CmsUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", identity='" + identity + '\'' +
                ", create_time='" + create_time + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
