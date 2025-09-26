package com.example.AttendanceProject.JWt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {


    private final String SECRET ="ThisIsASuperSecretKeyForJWT123456";

    private final Key SecretKeys= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


    public String generateToken(String username,String role){
        return Jwts.builder().setSubject(username).claim("Role",role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SecretKeys, SignatureAlgorithm.HS256).compact();
    }

    public String generateRole(String token){
        return Jwts.parserBuilder().setSigningKey(SecretKeys)
                .build().parseClaimsJws(token).getBody().get("Role",String.class);
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(SecretKeys)
                    .build().parseClaimsJws(token).getBody().getSubject();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(SecretKeys).build().parseClaimsJws(token)
                .getBody().getSubject();
    }
    public Boolean validateJwt(String token){
        try{
            extractUsername(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
