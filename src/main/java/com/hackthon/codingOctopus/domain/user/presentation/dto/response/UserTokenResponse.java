package com.hackthon.codingOctopus.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class UserTokenResponse {

    private String token;
    private String name;
    private String location;

}
