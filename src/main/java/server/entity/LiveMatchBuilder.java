package server.entity;

import bigtennis.entity.BigTennisLiveMatchList;
import org.openqa.selenium.WebElement;
import tabletennis.dao.WebElementProvider;
import tabletennis.entity.TableTennisLiveMatchList;

import java.util.ArrayList;
import java.util.List;

public class LiveMatchBuilder {

    private static final String INNER_TEXT_ATTRIBUTE = "innerText";
    private static final String SPLIT_SYMBOL = "/";

    public List<LiveMatch> getLiveMatchList(WebElementProvider webElementProvider) {
        List<WebElement> leaguesContentList = webElementProvider.getLiveLeagueContentElementList();
        List<LiveMatch> liveMatchList = new ArrayList<>();

        for(WebElement leagueContent : leaguesContentList) {
            liveMatchList.addAll(parseLeague(leagueContent, webElementProvider));
        }
       return liveMatchList;
    }

    public BigTennisLiveMatchList getBigTennisLiveMatchList(WebElementProvider webElementProvider) {
        return new BigTennisLiveMatchList(getLiveMatchList(webElementProvider));
    }

    public TableTennisLiveMatchList getTableTennisLiveMatchList(WebElementProvider webElementProvider) {
        return new TableTennisLiveMatchList(getLiveMatchList(webElementProvider));
    }

    private List<LiveMatch> parseLeague(WebElement leagueContent, WebElementProvider webElementProvider) {
        List<LiveMatch> leagueMatchList = new ArrayList<>();
        final String leagueName = webElementProvider.getLiveLeagueNameElement(leagueContent).getAttribute(INNER_TEXT_ATTRIBUTE);

        List<WebElement> matchContentList = webElementProvider.getLiveMatchElementList(leagueContent);
        for (WebElement matchContent : matchContentList) {
            leagueMatchList.add(parseLiveMatch(leagueName, matchContent,webElementProvider));
        }
        return leagueMatchList;
    }

    private LiveMatch parseLiveMatch(String leagueName, WebElement matchContent, WebElementProvider webElementProvider) {
        final LiveMatch liveMatch = new LiveMatch();
        liveMatch.setLeague(leagueName);

        String[] playerNames = parsePlayerNames(matchContent, webElementProvider);

        liveMatch.setPlayer1(playerNames[0]);
        liveMatch.setPlayer2(playerNames[1]);

        String[] scoreStrings = parseScores(matchContent,webElementProvider);
        liveMatch.setScores(scoreStrings);
        return liveMatch;
    }

    private static String parsePlayerName(String textFromPlayersCell) {
        final String REGEXP_COUNTRY_FROM = "(\\(*[a-zA-Zа-яА-Я]*\\))";
        String cleanPlayerName = textFromPlayersCell.replaceAll(REGEXP_COUNTRY_FROM, "");
        return cleanPlayerName.trim();
    }

    private String[] parsePlayerNames(WebElement matchElement, WebElementProvider webElementProvider) {
        List<WebElement> playerNameElementList = webElementProvider.getLiveP1P2NameElementList(matchElement);
        String[] playerNames = new String[2];

        playerNames[0] = parsePlayerName(playerNameElementList.get(0).getAttribute(INNER_TEXT_ATTRIBUTE));
        playerNames[1] = parsePlayerName(playerNameElementList.get(1).getAttribute(INNER_TEXT_ATTRIBUTE));

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
