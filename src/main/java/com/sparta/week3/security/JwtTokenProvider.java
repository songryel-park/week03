package com.sparta.week3.security;

import com.sparta.week3.domain.Detail;
import com.sparta.week3.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = Base64.getEncoder().encodeToString("Secret Key".getBytes());
    private final long ValidTime = 360000L;
    private UserRepository repository;
    @Autowired
    public JwtTokenProvider(UserRepository repository){
        this.repository = repository;
    }

    public String generateRefreshToken(){
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+600000L))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public String generateToken(Detail userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginId",userDetails.getUser().getLoginId());
        claims.put("loginPw",userDetails.getUser().getLoginPw());
        claims.put("id",userDetails.getUser().getId());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ValidTime))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Detail userDetails = new Detail(repository.findById(Long.parseLong(this.getUserPk(token))).get());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get("id").toString();
    }
}
