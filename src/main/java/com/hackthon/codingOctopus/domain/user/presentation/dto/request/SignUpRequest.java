package com.hackthon.codingOctopus.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class SignUpRequest {

    private String id;
    private String password;
    private String name;
    private String location;

}
