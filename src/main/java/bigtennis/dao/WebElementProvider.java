package bigtennis.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebElementProvider {

    private final ChromeDriver driver;

    private static final String SEARCHBOX_CLASS = "//*[@id=\"searchGames\"]";
    private static final String MATCHBOX_CLASS = "c-games__row_light";
    private static final String LEAGUES_CLASS = "c-games__name";
    private static final String TENNIS_BUTTON_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[1]/div/section/ul/li[5]/a";
    private static final String CALENDAR_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[1]";
    private static final String BUTTON_PREV_MONTH_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/header/span[1]";
    private static final String APPLY_DATE_BUTTON_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[5]/div";
    private static final String FREE_SPACE_XPATH = "//*[@id=\"router_app\"]/div/div[1]";
    private static final String LEFT_DATES_XPATH = "//*[@id='router_app']/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/div/*[.!='' and not(starts-with(@class,'cell day-header')) and not(starts-with(@class,'cell day disabled'))]";
    private static final String RAW_LEAGUE_DATA_CLASS = "c-games__col";
    private static final String RAW_LEAGUE_IDENTIFY_ATTRIBUTE = "sportid";
    private static final String EXPAND_ALL_MATCHES_BUTTON_XPATH = "//*[@id=\"router_app\"]/div/div[2]/div/div/div[2]/div[2]/div/div/div[9]/div" ;
        private static final String LEAGUE_NAME_CLASS = "c-games__row c-games__row_can-toggle active";
    private static final String PARENT_XPATH = "./..";

    public WebElement getTennisButton() {
        return generateWait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TENNIS_BUTTON_XPATH)));
    }

    public void waitUntilMatchesLoaded() {
        generateWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MATCHBOX_CLASS)));
    }

//    public List<WebElement> getRawLeagueDataList() {
//        return driver.findElements(By.className(RAW_LEAGUE_DATA_CLASS));
//    }

    public List<WebElement> getRawLeagueDataList() {
        List<WebElement> leagueNamesList = driver.findElements(By.className(RAW_LEAGUE_DATA_CLASS));
        leagueNamesList = leagueNamesList.stream().filter(webElement -> webElement.getAttribute(RAW_LEAGUE_IDENTIFY_ATTRIBUTE) != null).collect(Collectors.toList());
        return leagueNamesList;
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

    public List<WebElement> getLeaguesList() {
        return driver.findElements(By.className(LEAGUES_CLASS));
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

    public WebElement getExpandAllMatchesButton() {
        return driver.findElement(By.xpath(EXPAND_ALL_MATCHES_BUTTON_XPATH));
    }

    //endregion
}
