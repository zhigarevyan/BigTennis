package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.controller.CollectingController;
import server.controller.SocketConnectionPool;
import server.dao.LiveDAOProvider;
import server.entity.ServerTask;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStarter {
    private static final Logger logger = LogManager.getLogger(ServerStarter.class);
    private static ExecutorService pool = Executors.newFixedThreadPool(2);
    private static ExecutorService livePool = Executors.newCachedThreadPool();
    private static List<ServerTask> serverTaskList = new ArrayList<>();

    private static RerunTask rerunTask = new RerunTask();

    private static Timer updateTimer = new Timer();

    public static void main(String[] args) {

        logger.info("Сервер запущен. v0.15");
        logger.info(ManagementFactory.getRuntimeMXBean().getName());

        pool.submit(new SocketConnectionPool());
        pool.execute(CollectingController.getInstance());

        LiveDAOProvider liveDAOProvider = LiveDAOProvider.getInstance();

        //serverTaskList.add(getServerTask(liveDAOProvider.getTableTennisDAO()));
        //serverTaskList.add(getServerTask(liveDAOProvider.getBigTennisDAO()));

        updateTimer.schedule(rerunTask, 1000*20, 1000 * 20);
    }

    private static ServerTask getServerTask(Runnable task) {
        ServerTask serverTask = new ServerTask();
        serverTask.setTask(task);
        serverTask.setFuture(livePool.submit(task));

        return serverTask;
    }

    private static void getServerTask(ServerTask serverTask) {
        serverTask.setFuture(livePool.submit(serverTask.getTask()));
    }

    private static class RerunTask extends TimerTask {
        @Override
        public void run() {
            for (ServerTask serverTask : serverTaskList) {
                    if (serverTask.getFuture().isDone()) {
                        getServerTask(serverTask);
                    }
            }
        }
    }

}
