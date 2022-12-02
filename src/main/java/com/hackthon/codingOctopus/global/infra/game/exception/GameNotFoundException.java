package com.hackthon.codingOctopus.global.infra.game.exception;

import com.hackthon.codingOctopus.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class GameNotFoundException extends BusinessException {

    public static final GameNotFoundException EXCEPTION = new GameNotFoundException();

    private GameNotFoundException() {
        super(HttpStatus.NOT_FOUND, "경기를 찾을 수 없습니다");
    }
}
