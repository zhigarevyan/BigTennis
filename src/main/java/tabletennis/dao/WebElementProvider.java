package tabletennis.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebElementProvider {

    private final ChromeDriver driver;

    private final String NASTOLKA_BUTTON_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[1]/div/section/ul/li[7]/a";
    private final String SEARCHBOX_CLASS = "//*[@id=\"searchGames\"]";
    private final String MATCHBOX_CLASS = "c-games__row_light";
    private final String CALENDAR_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[1]";
    private final String BUTTON_PREV_MONTH_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/header/span[1]";
    private final String APPLY_DATE_BUTTON_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[5]/div";
    private final String FREE_SPACE_XPATH = "//*[@id=\"router_app\"]/div/div[1]";
    private final String LEFT_DATES_XPATH = "//*[@id='router_app']/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/div/*[.!='' and not(starts-with(@class,'cell day-header')) and not(starts-with(@class,'cell day disabled'))]";
    private final String MATCHBOX_LIVE_CLASS = "c-events-scoreboard";
    private final String LIVE_LEAGUE_CONTENT_ELEMENT_XPATH = "//*[@id='games_content']/div/div[1]/div/div";
    private final String LIVE_LEAGUE_NAME_ELEMENT_CLASS = "c-events__liga";
    private final String LIVE_MATCH_ELEMENTS_CLASS = "c-events__item_col";
    private final String LIVE_PLAYERS_SCORE_CLASS = "c-events-scoreboard__line";
    private final String LIVE_BETS_CLASS = "c-bets";
    private final String NAMEBOX_LIVE_CLASS = "c-events__team";

    public List<WebElement> getLiveMatchScoreCellList(WebElement matchContent) { return matchContent.findElements(By.className(LIVE_PLAYERS_SCORE_CLASS));
    }

    public List<WebElement> getLiveLeagueContentElementList() {
        return driver.findElements(By.xpath(LIVE_LEAGUE_CONTENT_ELEMENT_XPATH));
    }

    public WebElement getLiveLeagueNameElement(WebElement leagueContent) {
        return leagueContent.findElement(By.className(LIVE_LEAGUE_NAME_ELEMENT_CLASS));
    }

    public List<WebElement> getBetsList(WebElement leagueContent) {
        return leagueContent.findElements(By.className(LIVE_BETS_CLASS));
    }

    public List<WebElement> getLiveMatchElementList(WebElement leagueContent) {
        return leagueContent.findElements(By.className(LIVE_MATCH_ELEMENTS_CLASS));
    }

    public List<WebElement> getLiveP1P2NameElementList(WebElement matchContent) {
        return matchContent.findElements(By.className(NAMEBOX_LIVE_CLASS));
    }

    public List<WebElement> getPlayersLiveScoreList(WebElement matchContent) {
        return matchContent.findElements(By.className(LIVE_PLAYERS_SCORE_CLASS));
    }


    public WebElement getNastolkaButton() {
        return generateWait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NASTOLKA_BUTTON_XPATH)));
    }

    public void waitUntilMatchesLoaded() {
        generateWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MATCHBOX_CLASS)));
    }

    public void waitUntilLiveLoaded() {
        generateWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LIVE_LEAGUE_CONTENT_ELEMENT_XPATH)));
    }

    public List<WebElement> getLiveMatchList() {
        return driver.findElements(By.className(MATCHBOX_LIVE_CLASS));
    }

    public WebElement getCalendar() {
        return driver.findElement(By.xpath(CALENDAR_XPATH));
    }

    public WebElement getPrevMonthButton() {
        return driver.findElement(By.xpath(BUTTON_PREV_MONTH_XPATH));
    }

    public WebElement getApplyDateButton() {
        return driver.findElement(By.xpath(APPLY_DATE_BUTTON_XPATH));
    }

    public WebElement getFreeSpace() {
        return driver.findElement(By.xpath(FREE_SPACE_XPATH));
    }

    public WebElement getSearchBox() {
        return driver.findElement(By.xpath(SEARCHBOX_CLASS));
    }

    public List<WebElement> getMatchList() {
        return driver.findElements(By.className(MATCHBOX_CLASS));
    }

    public List<WebElement> getDayButtons() {
        return driver.findElements(By.xpath(LEFT_DATES_XPATH));
    }

    private WebDriverWait generateWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    //region GSC

    public WebElementProvider(ChromeDriver driver) {
        this.driver = driver;
    }

    //endregion
}
