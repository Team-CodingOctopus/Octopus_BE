package com.hackthon.codingOctopus.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String password;
    private String name;
    private String location;

}
