package server.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import server.api.model.AuthRequest;

import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
    //fields injection
    @Autowired
    private AuthenticationManager authenticationManager;

    //constructor injection
//    private final AuthenticationManager authenticationManager;
//    @Autowired
//    public JWTService(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

//    private AuthenticationManager authenticationManager;
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    private final String KEY = "MikeIsRunningForProgrammingBeginner";

    public String generateToken(AuthRequest request){
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authentication = authenticationManager.authenticate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println(authentication.getPrincipal());
            System.out.println(userDetails.getAuthorities());

            Calendar calendar = Calendar.getInstance();
            //token期限
            calendar.add(Calendar.HOUR, 1);

            Claims claims = Jwts.claims();
            claims.put("username", userDetails.getUsername());
            claims.put("authorities", userDetails.getAuthorities());
            claims.setExpiration(calendar.getTime());
            claims.setIssuer("FOM_CMS");

            System.out.println(claims);

            Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(secretKey)
                    .compact();

        }catch (ExpiredJwtException e) {
            return e.getMessage();
        }

    }

    public Map<String, Object> parseToken(String token){
        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

       JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
       Claims claims = parser
                .parseClaimsJws(token)
                .getBody();

       return claims.entrySet().stream()
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
