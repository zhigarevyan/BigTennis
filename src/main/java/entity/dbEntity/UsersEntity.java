package entity.dbEntity;

import javax.persistence.*;

@Entity
@Table(name = "Users", schema = "bigtennis", catalog = "")
public class UsersEntity {
    private int id;
    private String name;
    private String key;
    private int role;
    private UserRolesEntity userRolesByRole;

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

    @Basic
    @Column(name = "key", nullable = false, length = 45)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (role != that.role) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + role;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    public UserRolesEntity getUserRolesByRole() {
        return userRolesByRole;
    }

    public void setUserRolesByRole(UserRolesEntity userRolesByRole) {
        this.userRolesByRole = userRolesByRole;
    }
}
