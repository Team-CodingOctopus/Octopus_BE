package com.hackthon.codingOctopus.global.security.jwt;

import com.hackthon.codingOctopus.domain.user.entity.User;
import com.hackthon.codingOctopus.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public String generateAccessToken(Long id) {
        return generateToken(id.toString(), jwtProperties.getAccessExp());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Authentication getAuthentication(String token) {
        User userDetails = userRepository.findById(getTokenSubject(token)).orElseThrow();
        return new UserToken(userDetails);
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (RuntimeException e) {
            throw new RuntimeException("Token body Exception");
        }
    }

    private Long getTokenSubject(String token) {
        return Long.parseLong(getTokenBody(token).getSubject());
    }

    private String generateToken(String id, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

}
