package server.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.model.SendMailRequest;
import server.api.service.MailService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Tag(name = "Mail", description = "信箱相關服務")
public class MailController extends ApiController{

    @Autowired
    private MailService mailService;

    @PostMapping("/verifycode")
    @Operation(
            summary = "寄送驗證碼",
            description = "寄送驗證碼至信箱"
    )
    public ResponseEntity<Void> getVerifyCode(@Valid @RequestBody SendMailRequest request, HttpSession session)
    {
        mailService.sendVerifyCode(request, session);
        System.out.println("send");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sendmail")
    @Operation(
            summary = "寄送信件",
            description = "寄送信件至信箱"
    )
    public ResponseEntity<Void> sendMail(@Valid @RequestBody SendMailRequest request)
    {
        mailService.sendMail(request);
        System.out.println("send");
        return ResponseEntity.noContent().build();
    }
}
