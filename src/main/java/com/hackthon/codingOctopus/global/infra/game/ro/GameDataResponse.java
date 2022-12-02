package com.hackthon.codingOctopus.global.infra.game.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class GameDataResponse {

    private String home;
    private String homeLogo;
    private String away;
    private String awayLogo;
    private String homePer;
    private String awayPer;
    private String drawPer;
    private String time;

}
