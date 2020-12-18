package bigtennis.entity;

import bigtennis.entity.dbEntity.UserEntity;
import server.entity.impl.TransferElement;

public class User extends TransferElement {

    private String name;
    private String key;
    private Role role;

    public User(String name, String key, Role role) {
        this.name = name;
        this.key = key;
        this.role = role;
    }

    public User(UserEntity userEntity) {
        name = userEntity.getName();
        key = userEntity.getKey();
        role = new Role(userEntity.getUserRolesByRole());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
