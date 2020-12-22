package bigtennis.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    private String command;
    private HashMap<String, String> parameters = new HashMap<>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void putParameter(String key, String value) {
        parameters.put(key,value);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }
}
