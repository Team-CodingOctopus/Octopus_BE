package com.hackthon.codingOctopus.domain.user.repository;

import com.hackthon.codingOctopus.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);

}
