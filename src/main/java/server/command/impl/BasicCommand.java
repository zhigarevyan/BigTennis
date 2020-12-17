package server.command.impl;

import server.command.Command;
import server.entity.impl.TransferElement;

import java.util.HashMap;

public class BasicCommand implements Command {

    private static final long serialVersionUID = 1L;

    HashMap<String, String> parameters = new HashMap<>();

    public void putParameter(String key, String value) {
        parameters.put(key,value);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public TransferElement execute() {return null;}

}
