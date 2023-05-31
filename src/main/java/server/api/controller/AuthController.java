package server.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.model.AuthRequest;
import server.api.service.JWTService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "產生與解析Token")
public class AuthController {
    @Autowired
    private JWTService jwtService;

    @PostMapping
    @Operation(
            summary = "產生Token",
            description = "透過API拿到JWT Token"
    )
    @CrossOrigin("*")
    public ResponseEntity<Map<String, String>> issueToken(@Valid @RequestBody AuthRequest request) {

        String token = jwtService.generateToken(request);
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    @Operation(
            summary = "解析Token",
            description = "透過API解析JWT Token"
    )
    @CrossOrigin("*")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}
