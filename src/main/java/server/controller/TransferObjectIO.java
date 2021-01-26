package server.controller;

import server.entity.TransferObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TransferObjectIO {

    //private static final String host = "localhost";
    private static final String host = "185.135.80.244";
    private static final int port = 8030;

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public void send(TransferObject transferObject) throws IOException {
        objectOutputStream.writeObject(transferObject);
        objectOutputStream.flush();
    }

    public void init() throws IOException {
        socket = new Socket(host,port);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
    }

    public TransferObject get() throws IOException, ClassNotFoundException {
        return (TransferObject) objectInputStream.readObject();
    }

}
