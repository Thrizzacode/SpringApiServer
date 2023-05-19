package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.api.model.Member;
import server.api.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api")

public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/members")
    @CrossOrigin("*")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
}
