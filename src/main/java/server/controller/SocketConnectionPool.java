package server.controller;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketConnectionPool implements Runnable {

    private static final Logger logger = Logger.getLogger(SocketConnectionPool.class);
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private static final int poolSize = 500;
    private static final int port = 8030;

    public void turnOn() {
        pool = Executors.newFixedThreadPool(poolSize);

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerController(socket));
            }

        } catch (IOException e) {
            logger.error("Cant turn on connections controller | "+e.getMessage(),e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    logger.error("Cant close serverSocket | "+e.getMessage(),e);
                }
            }
        }
    }

    @Override
    public void run() {
            turnOn();
    }
}
