package com.hackthon.codingOctopus.domain.user.exception;

import com.hackthon.codingOctopus.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PasswordWrongException extends BusinessException {

    public static final PasswordWrongException EXCEPTION = new PasswordWrongException();

    private PasswordWrongException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다.");
    }
}
