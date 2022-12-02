package com.hackthon.codingOctopus.global.infra.game.presentation;

import com.hackthon.codingOctopus.global.infra.game.ro.GameDataResponse;
import com.hackthon.codingOctopus.global.infra.game.ro.GameResponse;
import com.hackthon.codingOctopus.global.infra.game.service.GameCrawlerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameCrawlerService gameCrawlerService;

    @ApiOperation("크롤링 경기 예측")
    @GetMapping("/match")
    public GameDataResponse getData(
            @RequestParam("country") String country
    ) {
        return gameCrawlerService.parserData(country);
    }

    @ApiOperation("경기 ID 가져오기")
    @GetMapping("")
    public Long getGame(
            @RequestParam("home") String home,
            @RequestParam("away") String away
    ) {
        return gameCrawlerService.getGame(home, away);
    }

    @ApiOperation("Home 팀 경기 투표")
    @GetMapping("/select/home/{game-id}")
    public GameResponse getSelectHome(
            @PathVariable("game-id") Long id
    ) {
        return gameCrawlerService.selectGame(id, "home");
    }

    @ApiOperation("Away 팀 경기 투표")
    @GetMapping("/select/away/{game-id}")
    public GameResponse getSelectAway(
            @PathVariable("game-id") Long id
    ) {
        return gameCrawlerService.selectGame(id, "away");
    }

}
