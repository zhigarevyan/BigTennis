package entity.dbEntity;

import javax.persistence.*;


@NamedQueries({

        @NamedQuery(name = "Match.byParams",
                query = "From MatchEntity match " +
                        "LEFT JOIN fetch match.player1 p1 " +
                        "LEFT JOIN fetch match.player2 p2 " +
                        "LEFT JOIN fetch match.result r " +
                        "LEFT JOIN fetch match.league l " +
                        "WHERE p1.name = :p1name " +
                        "and p2.name = :p2name " +
                        "and r.score = :score " +
                        "and r.set1 = :set1 " +
                        "and r.set2 = :set2 " +
                        "and r.set3 = :set3 " +
                        "and r.set4 = :set4 " +
                        "and r.set5 = :set5 " +
                        "and match.date = :date " +
                        "and l.name = :league " +
                        "order by match.date desc"),

        @NamedQuery(name = "Match.byId",
                query = "From MatchEntity match " +
                        "WHERE player1 = :p1id " +
                        "and player2 = :p2id " +
                        "and result = :resultid " +
                        "and match.date = :date " +
                        "and league = :leagueid " +
                        "order by match.date desc"),

        @NamedQuery(name = "Match.getMatches",
                query = "From MatchEntity match " +
                        "LEFT JOIN fetch match.player1 p1 " +
                        "LEFT JOIN fetch match.player2 p2 " +
                        "LEFT JOIN fetch match.result r " +
                        "LEFT JOIN fetch match.league l " +
                        "WHERE l.name like :league " +
                        "order by match.date desc"),

        @NamedQuery(name = "Match.get2PlMatches",
                query = "From MatchEntity match " +
                        "LEFT JOIN fetch match.player1 p1 " +
                        "LEFT JOIN fetch match.player2 p2 " +
                        "LEFT JOIN fetch match.result r " +
                        "LEFT JOIN fetch match.league l " +
                        "WHERE (p1.name = :p1name or p2.name = :p1name)" +
                        "and (p1.name = :p2name or p2.name = :p2name)" +
                        "and l.name like :league " +
                        "order by match.date desc"),

        @NamedQuery(name = "Match.getPlMatches",
                query = "From MatchEntity match " +
                        "LEFT JOIN fetch match.player1 p1 " +
                        "LEFT JOIN fetch match.player2 p2 " +
                        "LEFT JOIN fetch match.result r " +
                        "LEFT JOIN fetch match.league l " +
                        "WHERE (p1.name = :p1name or p2.name = :p1name) " +
                        "and l.name like :league " +
                        "order by match.date desc"),

        @NamedQuery(name = "Match.getPlMatchesByDate",
                query = "From MatchEntity match " +
                        "LEFT JOIN fetch match.player1 p1 " +
                        "LEFT JOIN fetch match.player2 p2 " +
                        "LEFT JOIN fetch match.result r " +
                        "LEFT JOIN fetch match.league l " +
                        "WHERE (p1.name = :p1name or p2.name = :p1name) " +
                        "and l.name like :league " +
                        "and match.date like :date " +
                        "order by match.date desc")
})



@Entity
@Table(name = "Matches", schema = "bigtennis", catalog = "")
public class MatchEntity {
    private int id;
    private String date;
    private PlayerEntity player1;
    private PlayerEntity player2;
    private ResultEntity result;
    private LeagueEntity league;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 16)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchEntity that = (MatchEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)
    public PlayerEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerEntity playersByPlayer1) {
        this.player1 = playersByPlayer1;
    }

    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id", nullable = false)
    public PlayerEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerEntity playersByPlayer2) {
        this.player2 = playersByPlayer2;
    }

    @ManyToOne
    @JoinColumn(name = "result", referencedColumnName = "id", nullable = false)
    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity resultByResult) {
        this.result = resultByResult;
    }


    @ManyToOne
    @JoinColumn(name = "league", referencedColumnName = "id", nullable = false)
    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }
}
