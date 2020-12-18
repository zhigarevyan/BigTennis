package bigtennis.entity.dbEntity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "User.byKey",
                query = "From UserEntity user " +
                        "LEFT JOIN fetch UserRoleEntity.id role " +
                        "WHERE user.key = :key ")
})


@Entity
@Table(name = "Users", schema = "bigtennis", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private UserRoleEntity userRolesByRole;
    private String key;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "key", nullable = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

        UserEntity that = (UserEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    public UserRoleEntity getUserRolesByRole() {
        return userRolesByRole;
    }

    public void setUserRolesByRole(UserRoleEntity userRolesByRole) {
        this.userRolesByRole = userRolesByRole;
    }

}






