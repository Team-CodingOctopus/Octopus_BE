package com.hackthon.codingOctopus.domain.user.exception;

import com.hackthon.codingOctopus.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BusinessException {

    public static final UserAlreadyExistsException EXCEPTION = new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 유저입니다.");
    }
}
