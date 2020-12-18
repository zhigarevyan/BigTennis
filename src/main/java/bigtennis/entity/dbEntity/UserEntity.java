package bigtennis.entity.dbEntity;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "User.byKey",
                query = "From UserEntity user " +
                        "WHERE user.deviceID = :key ")
})

@Entity
@Table(name = "Users", schema = "bigtennis", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private UserRoleEntity userRolesByRole;
    private String deviceID;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "device_id", nullable = false)
    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String key) {
        this.deviceID = key;
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
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(userRolesByRole, that.userRolesByRole) &&
                Objects.equals(deviceID, that.deviceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userRolesByRole, deviceID);
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






