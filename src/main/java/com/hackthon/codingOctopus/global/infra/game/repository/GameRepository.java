package com.hackthon.codingOctopus.global.infra.game.repository;

import com.hackthon.codingOctopus.global.infra.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByHomeAndAway(String home, String away);

}
