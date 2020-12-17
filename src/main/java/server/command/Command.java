package server.command;

import server.entity.impl.TransferElement;

import java.io.Serializable;

public interface Command extends Serializable {

    TransferElement execute();

}
