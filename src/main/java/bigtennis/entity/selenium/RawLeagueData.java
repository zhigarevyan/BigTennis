package bigtennis.entity.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RawLeagueData {

    String name;
    List<WebElement> rawMatchData = new ArrayList<>();

    private static final String LEAGUES_NAME = "c-games__name";
    private static final String MATCHBOX_CLASS = "c-games__row_light";

    public RawLeagueData(WebElement rawLeagueData) {
        name = rawLeagueData.findElement(By.className(LEAGUES_NAME)).getAttribute("innerText");
        rawMatchData.addAll(rawLeagueData.findElements(By.className(MATCHBOX_CLASS)));
    }

    public String getName() {
        return name;
    }

    public List<WebElement> getRawMatchDataList() {
        return rawMatchData;
    }

    public static void main(String[] args) {

    }
}
