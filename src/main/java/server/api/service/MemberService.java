package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.DAO.MemberDao;
import server.api.model.Member;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public List<Member> getAllMembers(){
        return memberDao.getAllMembers();
    }
}
