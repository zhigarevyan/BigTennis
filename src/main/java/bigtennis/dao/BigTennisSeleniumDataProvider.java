package bigtennis.dao;

import bigtennis.controller.BigTennisDataController;
import bigtennis.entity.SeleniumMatchBuilder;
import bigtennis.entity.SeleniumMatchList;
import server.exception.SeleniumInitException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class BigTennisSeleniumDataProvider {

    private static final Logger logger = Logger.getLogger(BigTennisSeleniumDataProvider.class);
    private List<String> leagues;

    public SeleniumMatchList getNewMatches() throws SeleniumInitException, InterruptedException {
        ChromeDriver driver = init();
        SeleniumMatchList seleniumMatchList = new SeleniumMatchList();
        WebElementProvider webElementProvider = new WebElementProvider(driver);
        driver.get("https://1xstavka.ru/results/");

        WebElement tennisButton = webElementProvider.getTennisButton();
        WebElement searchBox = webElementProvider.getSearchBox();
        tennisButton.click();

        setLeagues(getLeagues(webElementProvider));

            seleniumMatchList = loadLeagues(webElementProvider);
            Thread.sleep(500);
        driver.quit();
        return seleniumMatchList;
    }

    public void getMatchesByDate(int monthQuantity) throws SeleniumInitException, InterruptedException {
        ChromeDriver driver = init();
        SeleniumMatchList seleniumMatchList = new SeleniumMatchList();
        WebElementProvider webElementProvider = new WebElementProvider(driver);
        BigTennisDataController dataController = BigTennisDataController.getInstance();

        driver.get("https://1xstavka.ru/results/");
        WebElement nastolkaButton = webElementProvider.getTennisButton();
        nastolkaButton.click();

        boolean firstMonth = true;

        while (monthQuantity >= 0) {
            WebElement calendar = webElementProvider.getCalendar();
            WebElement freeSpace = webElementProvider.getFreeSpace();
            WebElement prevMonth = webElementProvider.getPrevMonthButton();
            WebElement applyDate = webElementProvider.getApplyDateButton();
            List<WebElement> daysToGet = webElementProvider.getDayButtons();

            if (firstMonth) {
                daysToGet.remove(daysToGet.size() - 1); //убрать нажатие на сегодняшний день
                firstMonth = false;
            }

            performClick(freeSpace, driver);
            for (int index = daysToGet.size(); index > 0; index--) { //загрузить все дни выбранного месяца

                WebElement lastDay = daysToGet.remove(index - 1);
                performClick(calendar, driver);
                performClick(lastDay, driver);
                //try-catch в случае, если произойдет ошибка иксбета. Рефреш страницы-возврат к текущему дню. Дописать на другие месяцы. Сработает только на 1 ошибку подряд.
                try {
                    webElementProvider.waitUntilMatchesLoaded(); //just wait, baby
                } catch (TimeoutException e) {
                    performClick(applyDate, driver);
                    performClick(applyDate, driver);

                }
                performClick(applyDate, driver);
                performClick(applyDate, driver);

                setLeagues(getLeagues(webElementProvider));
                seleniumMatchList = loadLeagues(webElementProvider);
                dataController.insertMatches(seleniumMatchList);
                logger.info("Вставлено " + seleniumMatchList.size() + " матчей");
            }

            performClick(calendar, driver);
            performClick(prevMonth, driver);
            monthQuantity--;
        }

        driver.quit();
    }

    private SeleniumMatchList loadLeagues(WebElementProvider webElementProvider) throws InterruptedException {
        SeleniumMatchBuilder seleniumMatchBuilder = new SeleniumMatchBuilder();
        SeleniumMatchList seleniumMatchList = new SeleniumMatchList();

        for (String leagueName : leagues) {
            WebElement searchBox = webElementProvider.getSearchBox();
            searchBox.sendKeys(leagueName);
            searchBox.sendKeys(Keys.ENTER);
            List<WebElement> matchList = webElementProvider.getMatchList();
            seleniumMatchList.addAll(seleniumMatchBuilder.getSeleniumMatchList(matchList, leagueName));
            sleep(1000);

            searchBox.sendKeys("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
        }

        logger.info("Отсканированно матчей: " + seleniumMatchList.size());

        return seleniumMatchList;
    }

    public static void performClick(WebElement element, WebDriver driver) {
        Actions builder = new Actions(driver);
        Actions action = builder
                .moveToElement(element)
                .click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.perform();
    }

    private ChromeDriver init() throws SeleniumInitException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();

//        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--lang=ru");

        try {
            return new ChromeDriver(options);
        } catch (RuntimeException e) {
            throw new SeleniumInitException("Error init chrome", e);
        }
    }

    public void setLeagues(List<String> leagues) {
        this.leagues = leagues;
    }

    private List<String> getLeagues(WebElementProvider webElementProvider) {
        List<String> leaguesList = new ArrayList<>();
        List<WebElement> elementList = webElementProvider.getLeaguesList();

        for (WebElement element : elementList) {
            leaguesList.add(element.getAttribute("innerText"));
        }

        return filterLeagues(leaguesList);
    }

    private List<String> filterLeagues(List<String> leagueList) {
        List<String> resultList = new ArrayList<>();
        List<String> explitedWordList = new ArrayList<>();

        explitedWordList.add("Специальные ставки");
        explitedWordList.add("Пары");
        explitedWordList.add("Cyber");

        for(String league : leagueList) {
            boolean founded = false;
            for(String explitedWord : explitedWordList) {
                if(league.contains(explitedWord)) {
                    founded = true;
                }
            }
            if(!founded) {
                resultList.add(league);
            }
        }
        return resultList;
    }

}
