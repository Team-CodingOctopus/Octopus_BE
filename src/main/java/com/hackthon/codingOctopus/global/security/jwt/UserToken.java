package com.hackthon.codingOctopus.global.security.jwt;

import com.hackthon.codingOctopus.domain.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserToken extends UsernamePasswordAuthenticationToken {

    public UserToken(User user) {
        super(user, null, null);
    }

}
