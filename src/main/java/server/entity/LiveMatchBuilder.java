package server.entity;

import org.openqa.selenium.WebElement;
import tabletennis.dao.WebElementProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveMatchBuilder {

    private static final String INNER_TEXT_ATTRIBUTE = "innerText";

    public List<LiveMatch> getLiveMatchList(WebElementProvider webElementProvider) {
        List<WebElement> leaguesContentList = webElementProvider.getLiveLeagueContentElementList();

        return getLeagueLiveMatchList(leaguesContentList, webElementProvider);
    }

    private List<LiveMatch> getLeagueLiveMatchList(List<WebElement> leaguesContentList, WebElementProvider webElementProvider) {
        List<LiveMatch> leagueLiveMatch = new ArrayList<>();

        for(WebElement matchElement : leaguesContentList) {
            leagueLiveMatch.add(parseLiveMatch(matchElement,webElementProvider));
        }

        return leagueLiveMatch;
    }

    private LiveMatch parseLiveMatch(WebElement matchElement, WebElementProvider webElementProvider) {
        final LiveMatch liveMatch = new LiveMatch();

        final String leagueName = webElementProvider.getLiveLeagueNameElement(matchElement).getAttribute(INNER_TEXT_ATTRIBUTE);
        liveMatch.setLeague(leagueName);

        String[] playerNames = parsePlayerNames(matchElement, webElementProvider);

        liveMatch.setPlayer1(playerNames[0]);
        liveMatch.setPlayer2(playerNames[1]);

        String[] scoreStrings = parseScores(matchElement,webElementProvider);
        liveMatch.setScores(scoreStrings);

        //set stats

        return liveMatch;
    }

    private String[] parsePlayerNames(WebElement matchElement, WebElementProvider webElementProvider) {
        List<WebElement> playerNameElementList = webElementProvider.getLiveP1P2NameElementList(matchElement);
        String[] playerNames = new String[2];

        playerNames[0] = playerNameElementList.get(0).getAttribute(INNER_TEXT_ATTRIBUTE);
        playerNames[1] = playerNameElementList.get(1).getAttribute(INNER_TEXT_ATTRIBUTE);

        return playerNames;
    }

    private String[] parseScores(WebElement matchElement, WebElementProvider webElementProvider) {
        List<WebElement> scoreElementList = webElementProvider.getLiveMatchScoreCellList(matchElement);
        String[] scores = new String[2];

        scores[0] = scoreElementList.get(0).getAttribute(INNER_TEXT_ATTRIBUTE);
        scores[1] = scoreElementList.get(1).getAttribute(INNER_TEXT_ATTRIBUTE);

        return scores;
    }

}
