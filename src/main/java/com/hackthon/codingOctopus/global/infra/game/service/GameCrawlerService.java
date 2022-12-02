package com.hackthon.codingOctopus.global.infra.game.service;

import com.hackthon.codingOctopus.global.infra.game.entity.Game;
import com.hackthon.codingOctopus.global.infra.game.exception.GameNotFoundException;
import com.hackthon.codingOctopus.global.infra.game.repository.GameRepository;
import com.hackthon.codingOctopus.global.infra.game.ro.GameDataResponse;
import com.hackthon.codingOctopus.global.infra.game.ro.GameResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameCrawlerService {

    private final GameRepository gameRepository;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:\\Program Files\\chromeDriver\\chromedriver.exe";

    @Transactional(rollbackFor = RuntimeException.class)
    public GameDataResponse parserData(String country) {

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.com/search?q=" + country + "월드컵" + "&sxsrf=ALiCzsaoTi2GJ2X5_2Bap1cBFumPZsmUiA%3A1670001043429&ei=kzGKY8TmGdiNmAXe9q2gCw&ved=0ahUKEwiE_N7Xttv7AhXYBqYKHV57C7QQ4dUDCA8&uact=5&oq=%EB%B8%8C%EB%9D%BC%EC%A7%88%EC%9B%94%EB%93%9C%EC%BB%B5&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQyCggAEIAEEIcCEBQyBQgAEIAEMgUILhCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoKCAAQRxDWBBCwA0oECEEYAEoECEYYAFD1Elj1EmCEFWgEcAF4AIAB-QGIAfkBkgEDMi0xmAEAoAEByAEKwAEB&sclient=gws-wiz-serp");
            WebElement button = driver.findElement(
                By.xpath("//*[@id=\"sports-app\"]/div/div[3]/div/div[2]/div/div/div/div[1]")
            );
            button.click();
            Thread.sleep(2000);

            WebElement button2 = driver.findElement(
                    By.cssSelector("#liveresults-sports-immersive__match-fullpage > div > div:nth-child(3) > div.nGzje > div:nth-child(2) > div > div.tb_h.ie7Asb.Hr51pb.imso-medium-font.TbbqEc.imso-ani.YPgUJe.B27Eaf > div > ol > li.imso-hide-overflow.tb_l.GSkImd.tb_st")
            );
            button2.click();
            Thread.sleep(2000);

            String home = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/div/div[1]/div[2]/div/span")).getText();
            String homeLogo = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/div/div[1]/div[1]/img")).getAttribute("src");
            String away = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/div/div[3]/div[2]/div/span")).getText();
            String awayLogo = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/div/div[3]/div[1]/img")).getAttribute("src");
            String homePer = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[2]/div/div[2]/div/div[3]/div/div/div/div/div[2]/div[2]/table[1]/tbody/tr[2]/td[1]")).getText();
            String awayPer = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[2]/div/div[2]/div/div[3]/div/div/div/div/div[2]/div[2]/table[1]/tbody/tr[2]/td[2]")).getText();
            String drawPer = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[2]/div/div[2]/div/div[3]/div/div/div/div/div[2]/div[2]/table[1]/tbody/tr[2]/td[3]")).getText();
            String time = driver.findElement(By.xpath("/html/body/div[7]/div/div[6]/div/div[2]/span/div/div/div[3]/div/div[3]/div[4]/div[1]/div[2]/div/div/div/div[1]/div/div[1]/div/span[2]")).getText();

            GameDataResponse response = GameDataResponse.builder()
                    .home(home)
                    .homeLogo(homeLogo)
                    .away(away)
                    .awayLogo(awayLogo)
                    .homePer(homePer)
                    .awayPer(awayPer)
                    .drawPer(drawPer)
                    .time(time)
                    .build();
            gameRepository.findByHomeAndAway(home, away)
                    .orElseGet(() -> {
                            Game game = Game.builder()
                                .home(home)
                                .away(away)
                            .build();
                            gameRepository.save(game);
                        return null;
                    });
            return response;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    @Transactional(readOnly = true)
    public Long getGame(String home, String away) {
        Game game = gameRepository.findByHomeAndAway(home, away)
                .orElseThrow(() -> GameNotFoundException.EXCEPTION);

        return game.getId();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public GameResponse selectGame(Long id, String team) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> GameNotFoundException.EXCEPTION);
        game.plusNumber();
        if(team.equals("home")) {
            game.plusHome();
        } else {
            game.plusAway();
        }
        game = gameRepository.save(game);

        return GameResponse.builder()
                .home(game.getHome())
                .away(game.getAway())
                .homePer((int)(((double)((double)game.getHomePer() / (double)game.getNumber())) * 100))
                .awayPer((int) (((double)((double)game.getAwayPer() / (double)game.getNumber())) * 100))
                .build();
    }

}
