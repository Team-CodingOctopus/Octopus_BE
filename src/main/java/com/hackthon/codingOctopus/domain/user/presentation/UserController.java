package com.hackthon.codingOctopus.domain.user.presentation;

import com.hackthon.codingOctopus.domain.user.presentation.dto.request.SignInRequest;
import com.hackthon.codingOctopus.domain.user.presentation.dto.request.SignUpRequest;
import com.hackthon.codingOctopus.domain.user.presentation.dto.response.UserTokenResponse;
import com.hackthon.codingOctopus.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(
            @RequestBody SignUpRequest request
    ) {
        userService.signUp(request);
    }

    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    public UserTokenResponse signIn(
            @RequestBody SignInRequest request
    ) {
        return userService.signIn(request);
    }

}
