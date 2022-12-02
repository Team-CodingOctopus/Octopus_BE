package com.hackthon.codingOctopus.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class SignInRequest {

    private String id;
    private String password;

}
