package com.fms.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fms.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class JwtService {
    @Value("${jwtSecret}")
    private String jwtSecret;
    public String createJwtToken(User user){

        return  JWT.create()
                .withIssuer("Vanilla")
                .withSubject("SomethingNnice")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .withJWTId(user.getId().toString())
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(Algorithm.HMAC256(this.jwtSecret));
    }
}
