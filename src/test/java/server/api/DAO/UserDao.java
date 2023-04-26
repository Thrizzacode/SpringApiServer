package server.api.DAO;
import server.api.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void addUser(User pUser){
//        jdbcTemplate.update("INSERT INTO user(id, user_name) "
//                + "VALUES (?,?)", pUser.getId(), pUser.getUserName());
//    }

    public Object getUser() {
        Object a ;

        a = jdbcTemplate.queryForObject("select id,user_name from user", AppUser.class);
        return a;
    }
}
