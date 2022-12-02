package com.hackthon.codingOctopus.global.infra.game.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class GameResponse {

    private String home;
    private String away;
    private int homePer;
    private int awayPer;

}
