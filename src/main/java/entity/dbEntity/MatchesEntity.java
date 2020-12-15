package entity.dbEntity;

import javax.persistence.*;

@Entity
@Table(name = "Matches", schema = "bigtennis", catalog = "")
public class MatchesEntity {
    private int id;
    private int player1;
    private int player2;
    private int result;
    private String date;
    private int league;
    private Integer courtType;
    private PlayersEntity playersByPlayer1;
    private PlayersEntity playersByPlayer2;
    private ResultsEntity resultsByResult;
    private LeaguesEntity leaguesByLeague;
    private CourtTypesEntity courtTypesByCourtType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "player1", nullable = false)
    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    @Basic
    @Column(name = "player2", nullable = false)
    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    @Basic
    @Column(name = "result", nullable = false)
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 16)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "league", nullable = false)
    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    @Basic
    @Column(name = "court_type", nullable = true)
    public Integer getCourtType() {
        return courtType;
    }

    public void setCourtType(Integer courtType) {
        this.courtType = courtType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchesEntity that = (MatchesEntity) o;

        if (id != that.id) return false;
        if (player1 != that.player1) return false;
        if (player2 != that.player2) return false;
        if (result != that.result) return false;
        if (league != that.league) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (courtType != null ? !courtType.equals(that.courtType) : that.courtType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + player1;
        result1 = 31 * result1 + player2;
        result1 = 31 * result1 + result;
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + league;
        result1 = 31 * result1 + (courtType != null ? courtType.hashCode() : 0);
        return result1;
    }

    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)
    public PlayersEntity getPlayersByPlayer1() {
        return playersByPlayer1;
    }

    public void setPlayersByPlayer1(PlayersEntity playersByPlayer1) {
        this.playersByPlayer1 = playersByPlayer1;
    }

    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id", nullable = false)
    public PlayersEntity getPlayersByPlayer2() {
        return playersByPlayer2;
    }

    public void setPlayersByPlayer2(PlayersEntity playersByPlayer2) {
        this.playersByPlayer2 = playersByPlayer2;
    }

    @ManyToOne
    @JoinColumn(name = "result", referencedColumnName = "id", nullable = false)
    public ResultsEntity getResultsByResult() {
        return resultsByResult;
    }

    public void setResultsByResult(ResultsEntity resultsByResult) {
        this.resultsByResult = resultsByResult;
    }

    @ManyToOne
    @JoinColumn(name = "league", referencedColumnName = "id", nullable = false)
    public LeaguesEntity getLeaguesByLeague() {
        return leaguesByLeague;
    }

    public void setLeaguesByLeague(LeaguesEntity leaguesByLeague) {
        this.leaguesByLeague = leaguesByLeague;
    }

    @ManyToOne
    @JoinColumn(name = "court_type", referencedColumnName = "id")
    public CourtTypesEntity getCourtTypesByCourtType() {
        return courtTypesByCourtType;
    }

    public void setCourtTypesByCourtType(CourtTypesEntity courtTypesByCourtType) {
        this.courtTypesByCourtType = courtTypesByCourtType;
    }
}
