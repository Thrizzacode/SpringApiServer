package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.model.Member;
import server.api.repository.MemberRepository;

import javax.transaction.Transactional;

@Transactional
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Iterable<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member editMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

}
