package com.hackthon.codingOctopus.domain.user.service;

import com.hackthon.codingOctopus.domain.user.entity.User;
import com.hackthon.codingOctopus.domain.user.exception.PasswordWrongException;
import com.hackthon.codingOctopus.domain.user.exception.UserAlreadyExistsException;
import com.hackthon.codingOctopus.domain.user.exception.UserNotFoundException;
import com.hackthon.codingOctopus.domain.user.presentation.dto.request.SignInRequest;
import com.hackthon.codingOctopus.domain.user.presentation.dto.request.SignUpRequest;
import com.hackthon.codingOctopus.domain.user.presentation.dto.response.UserTokenResponse;
import com.hackthon.codingOctopus.domain.user.repository.UserRepository;
import com.hackthon.codingOctopus.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(rollbackFor = RuntimeException.class)
    public void signUp(SignUpRequest request) {
        userRepository.findByUserId(request.getId())
                .ifPresent(m -> {throw UserAlreadyExistsException.EXCEPTION;});

        User user = User.builder()
                .userId(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .location(request.getLocation())
                .build();
        userRepository.save(user);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public UserTokenResponse signIn(SignInRequest request) {
        User user = userRepository.findByUserId(request.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return UserTokenResponse.builder()
                    .token(jwtProvider.generateAccessToken(user.getId()))
                    .name(user.getName())
                    .location(user.getLocation())
                    .build();
        } else {
            throw PasswordWrongException.EXCEPTION;
        }

    }

}
