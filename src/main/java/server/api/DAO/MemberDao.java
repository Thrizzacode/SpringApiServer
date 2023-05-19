package server.api.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import server.api.model.Member;

import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void addMember(Member pMember){
//        jdbcTemplate.update("INSERT INTO cms_membership(username, password) "
//                + "VALUES (?,?)", pMember.getUsername(), pMember.getPassword());
//    }
//
//
//    public void deleteMember(String id){
//        jdbcTemplate.update("DELETE FROM cms_membership WHERE id = ?", id);
//    }
//
//    public Member getMember(String username) {
//        Member member = jdbcTemplate.queryForObject("SELECT * FROM cms_membership WHERE username= ?", new Object[]{username}, new MemberRowMapper());
//        return member;
//    }
//
//    public Member getMember(int id) {
//        Member member = jdbcTemplate.queryForObject("SELECT * FROM cms_membership WHERE id= ?", new Object[]{id}, new MemberRowMapper());
//        return member;
//    }
//
//    public void updateMember(Member pMember){
//        jdbcTemplate.update("UPDATE cms_membership SET username = ?, password = ? WHERE id = ?", pMember.getUsername(), pMember.getPassword(), pMember.getId());
//    }

    public List<Member> getAllMembers(){
        List<Member> members = jdbcTemplate.query("SELECT * FROM cms_membership", new MemberRowMapper());
        return members;
    }

    static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            member.setPhone(rs.getInt("phone"));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }
}
