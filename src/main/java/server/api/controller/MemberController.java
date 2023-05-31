package server.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.api.model.Member;
import server.api.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/members")
    @Operation(
            summary = "取得所有會員資料",
            description = "取得所有會員資料",
            responses = {
                   @ApiResponse(
                            responseCode = "200",
                            description = "成功取得所有會員資料"
                    ),
            }
    )
    @CrossOrigin("*")
    public Iterable<Member> getAll() {
        return memberService.getAll();
    }

    @PostMapping("/members/add")
    @Operation(
            summary = "新增會員資料",
            description = "新增會員資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功新增會員資料"
                    ),
            }
    )
    @CrossOrigin("*")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @PutMapping("/members/edit")
    @Operation(
            summary = "編輯會員資料",
            description = "編輯會員資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功編輯會員資料"
                    ),
            }
    )
    @CrossOrigin("*")
    public Member editMember(@RequestBody Member member) {
        return memberService.editMember(member);
    }

    @DeleteMapping("/members/delete/{id}")
    @Operation(
            summary = "刪除會員資料",
            description = "刪除會員資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功刪除會員資料"
                    ),
            }
    )
    @CrossOrigin("*")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "Deleted";
    }
}
