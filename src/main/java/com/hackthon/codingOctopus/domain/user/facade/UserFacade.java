package com.hackthon.codingOctopus.domain.user.facade;

import com.hackthon.codingOctopus.domain.user.entity.User;
import com.hackthon.codingOctopus.domain.user.exception.UserNotFoundException;
import com.hackthon.codingOctopus.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User queryUser(boolean withPersistence) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(withPersistence) {
            return userRepository.findById(user.getId())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        }else {
            return user;
        }
    }

    public User queryUser() {
        return queryUser(false);
    }

    public User queryUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
