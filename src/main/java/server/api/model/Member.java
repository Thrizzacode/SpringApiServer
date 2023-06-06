package server.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_membership")
public class Member {

    @Id
    @Schema(description = "會員ID")
    private long id;
    @Schema(description = "會員姓名")
    private String name;
    @Schema(description = "會員電話")
    private String phone;
    @Schema(description = "會員地址")
    private String address;
    @Schema(description = "會員建立者")
    private String creator;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
