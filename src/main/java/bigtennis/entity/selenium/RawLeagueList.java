package bigtennis.entity.selenium;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RawLeagueList {

    private List<RawLeagueData> rawLeagueDataList = new ArrayList<>();

    private static final List<String> excludedLeagueNames = getExcludedLeagueNamesList();

    public RawLeagueList(List<WebElement> rawLeagueData) {
        rawLeagueData.forEach(webElement -> rawLeagueDataList.add(new RawLeagueData(webElement)));
    }

    private List<RawLeagueData> getFilteredLeagues(List<String> excludedNames) {
        return rawLeagueDataList.stream().filter(element -> excludedNames.stream().noneMatch(element.name::equals)).collect(Collectors.toList());
    }

    public List<RawLeagueData> getRawLeagueDataList() {
        return getFilteredLeagues(excludedLeagueNames);
    }

    private static List<String> getExcludedLeagueNamesList() {
        List<String> excludedNames = new ArrayList<>();
        excludedNames.add("Специальные ставки");
        excludedNames.add("Пары");
        excludedNames.add("Cyber");
        return excludedNames;
    }

}
