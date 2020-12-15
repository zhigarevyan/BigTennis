package entity.dbEntity;

import javax.persistence.*;
import java.util.Collection;

@NamedQueries({

        @NamedQuery(name = "CourtType.byName",
                query = "From CourtTypeEntity courtTypes " +
                        "WHERE name = :name ")
})

@Entity
@Table(name = "CourtTypes", schema = "bigtennis", catalog = "")
public class CourtTypeEntity {
    private int id;
    private String name;
    private Collection<MatchesEntity> matchesById;

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

        CourtTypeEntity that = (CourtTypeEntity) o;

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

    @OneToMany(mappedBy = "courtTypesByCourtType")
    public Collection<MatchesEntity> getMatchesById() {
        return matchesById;
    }

    public void setMatchesById(Collection<MatchesEntity> matchesById) {
        this.matchesById = matchesById;
    }
}
