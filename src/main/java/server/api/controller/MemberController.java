package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.api.model.Member;
import server.api.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/members")
    @CrossOrigin("*")
    public Iterable<Member> getAll() {
        return memberService.getAll();
    }

    @PostMapping("/members/add")
    @CrossOrigin("*")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @DeleteMapping("/members/delete/{id}")
    @CrossOrigin("*")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "Deleted";
    }
}
