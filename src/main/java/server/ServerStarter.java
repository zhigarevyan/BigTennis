package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.controller.CollectingController;
import server.controller.SocketConnectionPool;
import server.dao.LiveDAO;
import server.dao.LiveDAOProvider;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStarter {
    private static final Logger logger = LogManager.getLogger(ServerStarter.class);
    private static ExecutorService pool;


    public static void main(String[] args) {

        logger.info("Сервер запущен. v0.15");
        logger.info(ManagementFactory.getRuntimeMXBean().getName());
        pool = Executors.newFixedThreadPool(4);
        LiveDAOProvider liveDAOProvider = LiveDAOProvider.getInstance();
        pool.execute(new SocketConnectionPool());
        pool.execute(CollectingController.getInstance());
        pool.execute(liveDAOProvider.getTableTennisDAO());
        pool.execute(liveDAOProvider.getTableTennisDAO());
    }


}
