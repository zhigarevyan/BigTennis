package bigtennis.entity;

public class User {
    private String name;
    private String key;
    private Role role;

    public User(String name, String key, Role role) {
        this.name = name;
        this.key = key;
        this.role = role;
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
