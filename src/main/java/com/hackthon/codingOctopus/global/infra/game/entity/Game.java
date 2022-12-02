package com.hackthon.codingOctopus.global.infra.game.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String home;

    private String away;

    @ColumnDefault("0")
    private int homePer;
    public void plusHome() {
        this.homePer++;
    }

    @ColumnDefault("0")
    private int awayPer;
    public void plusAway() {
        this.awayPer++;
    }

    @ColumnDefault("0")
    private int number;
    public void plusNumber() {
        this.number++;
    }

}
