package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.api.model.Member;
import server.api.model.UserIdentity;
import server.api.repository.MemberRepository;

import javax.transaction.Transactional;

@Transactional
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private UserIdentity userIdentity;

    //查詢所有會員
    public Iterable<Member> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
    //查詢單一會員
    public Member getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    //新增會員
    public Member addMember(Member member) {
        member.setCreator(userIdentity.getUsername());
        return memberRepository.save(member);
    }

    //編輯會員
    public Member editMember(Member member) {
        return memberRepository.save(member);
    }

    //
    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

}
