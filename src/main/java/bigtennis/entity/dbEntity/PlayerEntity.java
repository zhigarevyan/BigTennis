package bigtennis.entity.dbEntity;

import javax.persistence.*;
import java.util.Collection;

@NamedQueries({

        @NamedQuery(name = "Player.byName",
                query = "From PlayerEntity players " +
                        "WHERE name = :name ")

})



@Entity
@Table(name = "Players", schema = "bigtennis", catalog = "")
public class PlayerEntity {
    private int id;
    private String name;
    private Collection<MatchEntity> matchesByPlayer1;
    private Collection<MatchEntity> matchesByPlayer2;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @OneToMany(mappedBy = "player1")
    public Collection<MatchEntity> getMatchesByPlayer1() {
        return matchesByPlayer1;
    }

    public void setMatchesByPlayer1(Collection<MatchEntity> matchesLSByIdplayers) {
        this.matchesByPlayer1 = matchesLSByIdplayers;
    }

    @OneToMany(mappedBy = "player2")
    public Collection<MatchEntity> getMatchesByPlayer2() {
        return matchesByPlayer2;
    }

    public void setMatchesByPlayer2(Collection<MatchEntity> matchesLSByIdplayers_0) {
        this.matchesByPlayer2 = matchesLSByIdplayers_0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
