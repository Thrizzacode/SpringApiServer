package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Iterable<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        member.setCreator(userIdentity.getUsername());
        return memberRepository.save(member);
    }

    public Member editMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

}
