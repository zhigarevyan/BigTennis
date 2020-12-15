package bigtennis.dao;

import bigtennis.entity.SeleniumMatch;
import bigtennis.entity.SeleniumMatchList;
import bigtennis.entity.StringResult;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumMatchBuilder {

    final static String SPLIT_SYMBOL = "/";

    public SeleniumMatchList getSeleniumMatchList(List<WebElement> matchList, String league) {
        final int DATE_INDEX = 0;
        final int PLAYERS_INDEX = 1;
        final int SCORE_INDEX = 2;
        final int ADDITIONAL_DATA_INDEX = 3;
        final int PLAYER1_INDEX = 0;
        final int PLAYER2_INDEX = 1;

        SeleniumMatchList seleniumMatchList = new SeleniumMatchList();

        for (WebElement elements : matchList) {
            String getInput = elements.getAttribute("innerText");
            String[] input = getInput.split("\\r?\\n");

            if (!validation(input)) {
                continue;
            }

            SeleniumMatch seleniumMatch = new SeleniumMatch();

            seleniumMatch.setDate(dateToDB(input[DATE_INDEX]));

            String[] players = playersToDBForm(input[PLAYERS_INDEX]);

            if (players.length <= 1) {
                continue;
            }

            seleniumMatch.setPlayer1(players[PLAYER1_INDEX]);
            seleniumMatch.setPlayer2(players[PLAYER2_INDEX]);

            seleniumMatch.setResult(scoreToDBForm(input[SCORE_INDEX]));

            if (input.length > ADDITIONAL_DATA_INDEX) { //if there are additional data

                String court = extractCourt(input[ADDITIONAL_DATA_INDEX]);

                if (court == null) {
                    court = "null";
                }
                seleniumMatch.setCourtType(court);
            }

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
        if (matchDetails[2].contains("0:0")) {
            return false;
        }

        return true;
    }

    private String extractCourt(String additionalData) {
        List<String> courtTypes = new ArrayList<>();
        courtTypes.add("Хард");
        courtTypes.add("Грунт");
        courtTypes.add("Трава");

        for (String courtType : courtTypes) {
            if (additionalData.contains(courtType))
                return courtType;
        }
        return null;
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
        String cleanPlayerNames = textFromPlayersCell.replaceAll(REGEXP_COUNTRY_FROM, "");
        cleanPlayerNames = cleanPlayerNames.replaceAll(REGEXP_SEPARATOR, SPLIT_SYMBOL);
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

            while (setMatcher.find()) {
                String set = setMatcher.group();
                stringResult.setSet(index, set);
                index++;
            }
        }
        return stringResult;
    }
}
