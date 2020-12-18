package bigtennis.entity;

import bigtennis.entity.dbEntity.UserRoleEntity;

public class Role {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(UserRoleEntity userRoleEntity) {
        this.id = userRoleEntity.getId();
        this.name = userRoleEntity.getName();
    }

}
