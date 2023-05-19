package server.api.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import server.api.model.User;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User pUser){
        jdbcTemplate.update("INSERT INTO user(username, password) "
                + "VALUES (?,?)", pUser.getUsername(), pUser.getPassword());
    }

    //刪除使用者
    public void deleteUser(String id){
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

    public User getUser(String username) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM cms_user WHERE username= ?", new Object[]{username}, new UserRowMapper());
        return user;
    }
    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setIdentity(rs.getString("identity"));
            user.setCreate_time(rs.getString("create_time"));
            return user;
        }
    }

    public List<User> getAllUsers(){
        List<User> users = jdbcTemplate.query("SELECT * FROM cms_user", new UserRowMapper());
        return users;
    }
}
