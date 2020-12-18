package bigtennis.entity;

import bigtennis.entity.dbEntity.UserEntity;
import server.entity.impl.TransferElement;

public class User extends TransferElement {

    private String name;
    private String deviceID;
    private Role role;

    public User(String name, String deviceID, Role role) {
        this.name = name;
        this.deviceID = deviceID;
        this.role = role;
    }

    public User(UserEntity userEntity) {
        name = userEntity.getName();
        deviceID = userEntity.getDeviceID();
        role = new Role(userEntity.getUserRolesByRole());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }




}
