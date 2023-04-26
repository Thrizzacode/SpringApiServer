package server.api.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.api.model.AppUser;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(AppUser pUser){
        jdbcTemplate.update("INSERT INTO user(username, password) "
                + "VALUES (?,?)", pUser.getUserName(), pUser.getPassword());
    }

    public Object getAllUser() {
        Object a ;
        a = jdbcTemplate.queryForList("select id,username,password from user");
        return a;
    }
    public AppUser getUser() {

        return (AppUser)jdbcTemplate.queryForList("select id,username,password from user");

    }
}
