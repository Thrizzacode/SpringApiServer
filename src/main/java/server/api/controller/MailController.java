package server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.model.SendMailRequest;
import server.api.service.MailService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sendmail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<Void> sendMail(@Valid @RequestBody SendMailRequest request)
    {
        mailService.sendMail(request);
        System.out.println("send");
        return ResponseEntity.noContent().build();
    }
}
