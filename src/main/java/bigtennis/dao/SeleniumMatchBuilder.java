package bigtennis.dao;

import bigtennis.entity.SeleniumMatch;
import bigtennis.entity.SeleniumMatchList;
import bigtennis.entity.StringResult;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumMatchBuilder {

    final static String SPLIT_SYMBOL = "/";

    public SeleniumMatchList getSeleniumMatchList(List<WebElement> matchList, String league) {
        SeleniumMatchList seleniumMatchList = new SeleniumMatchList();

        for (WebElement elements : matchList) {
            String getInput = elements.getAttribute("innerText");
            String[] input = getInput.split("\\r?\\n");

            if (!validation(input)) {
                continue;
            }

            SeleniumMatch seleniumMatch = new SeleniumMatch();

            seleniumMatch.setDate(dateToDB(input[0]));

            String[] players = playersToDBForm(input[1]);
            seleniumMatch.setPlayer1(players[0]);
            seleniumMatch.setPlayer2(players[1]);

            seleniumMatch.setResult(scoreToDBForm(input[2]));

            seleniumMatch.setLeague(league);

            seleniumMatchList.add(seleniumMatch);
        }

        return seleniumMatchList;
    }

    private boolean validation(String[] matchDetails) {
        if (matchDetails[1].contains("/")) {
            return false;
        }
        if (matchDetails[1].contains("0:0")) {
            return false;
        }

        return true;
    }

    private String dateToDB(String string) {
        String[] result = string.split(" ");
        String[] tempString = result[0].split("\\.");
        return "2020-" + tempString[1] + '-' + tempString[0] + " " + result[1];
    }

    private String[] playersToDBForm(String textFromPlayersCell) {
        String[] listOfPlayers;
        String preparedPlayerNames = parsePlayerNames(textFromPlayersCell);

        listOfPlayers = preparedPlayerNames.split(SPLIT_SYMBOL);
        return listOfPlayers;
    }

    private static String parsePlayerNames(String textFromPlayersCell) {
        final String REGEXP_COUNTRY_FROM = "(\\(*[a-zA-Zа-яА-Я]*\\))";
        final String REGEXP_SEPARATOR = "(\\s+-\\s+)";
        String cleanPlayerNames = textFromPlayersCell.replaceAll(REGEXP_COUNTRY_FROM,"");
        cleanPlayerNames = cleanPlayerNames.replaceAll(REGEXP_SEPARATOR,SPLIT_SYMBOL);
        return cleanPlayerNames;
    }

    private StringResult scoreToDBForm(String rawString) {
        final String REGEXP_SCORE = "^(\\d+:\\d+)";
        final String REGEXP_SETS = "\\((\\d+:\\d+,?)+\\)";
        final String REGEXP_SET = "\\d+:\\d+";
        final StringResult stringResult = new StringResult();

        final Pattern scorePattern = Pattern.compile(REGEXP_SCORE);
        final Pattern setsPattern = Pattern.compile(REGEXP_SETS);
        final Pattern setPattern = Pattern.compile(REGEXP_SET);

        Matcher scoreMatcher = scorePattern.matcher(rawString);
        Matcher setsMatcher = setsPattern.matcher(rawString);
        Matcher setMatcher;

        String sets;

        if (scoreMatcher.find() && setsMatcher.find()) {
            int index = 0;

            stringResult.setScore(scoreMatcher.group());
            sets = setsMatcher.group();

            setMatcher = setPattern.matcher(sets);

            while(setMatcher.find()) {
                String set = setMatcher.group();
                stringResult.setSet(index,set);
                index++;
            }
        }
        return stringResult;
    }
}
