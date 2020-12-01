package entity.dbEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Players", schema = "bigtennis", catalog = "")
public class PlayersEntity {
    private int id;
    private String name;
    private Collection<MatchesEntity> matchesById;
    private Collection<MatchesEntity> matchesById_0;

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

        PlayersEntity that = (PlayersEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "playersByPlayer1")
    public Collection<MatchesEntity> getMatchesById() {
        return matchesById;
    }

    public void setMatchesById(Collection<MatchesEntity> matchesById) {
        this.matchesById = matchesById;
    }

    @OneToMany(mappedBy = "playersByPlayer2")
    public Collection<MatchesEntity> getMatchesById_0() {
        return matchesById_0;
    }

    public void setMatchesById_0(Collection<MatchesEntity> matchesById_0) {
        this.matchesById_0 = matchesById_0;
    }
}
