package server.dao;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import server.entity.LiveMatchBuilder;
import tabletennis.dao.WebElementProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LiveDAO implements Runnable {

    private static final Logger logger = Logger.getLogger(LiveDAO.class);

    private volatile boolean isOkay;

    private static final ChromeOptions options = initOptions();
    private final String URL;
    private ChromeDriver liveChromeDriver;

    protected WebElementProvider webElementProvider;
    protected final static LiveMatchBuilder liveMatchBuilder = new LiveMatchBuilder();

    private ScheduledExecutorService updateExecutor;
    private ScheduledExecutorService collectExecutor;

    public LiveDAO(String URL) {
        this.URL = URL;
    }

    public void updateLiveList() {
    }


    @Override
    public void run() {
        isOkay = true;
        try {
            liveChromeDriver = new ChromeDriver(options);
            webElementProvider = new WebElementProvider(liveChromeDriver);

            UpdateThread updateThread = new UpdateThread();
            updateExecutor = Executors.newSingleThreadScheduledExecutor();
            collectExecutor = Executors.newSingleThreadScheduledExecutor();

            updateExecutor.scheduleWithFixedDelay(updateThread, 0, 5, TimeUnit.MINUTES);
            System.out.println("UPDATE");
            while (isOkay);

        } catch (RuntimeException e) {
            isOkay = false;
            logger.error(String.format("Live: Error init chrome \n Exception: %s \n Caused by: %s", e.getMessage(), e.getCause().getMessage()), e);

        } finally {
            liveChromeDriver.close();
            collectExecutor.shutdownNow();
            updateExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void initPage() {
        liveChromeDriver.get(URL);
    }


    private static ChromeOptions initOptions() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--lang=ru");
        return options;
    }


    private class UpdateThread extends Thread {
        private final CollectThread collectThread = new CollectThread();

        @Override
        public void run() {
            if(!isOkay) {
                Thread.currentThread().interrupt();
            }
            try {
                initPage();
                sleep(5000);

                webElementProvider.waitUntilLiveLoaded();

                collectExecutor.scheduleWithFixedDelay(collectThread, 0, 20, TimeUnit.SECONDS);
            } catch (Exception e) {
                isOkay = false;
                logger.error("UpdateException: "+e.getMessage()+"|"+e.getCause().getMessage());
                collectThread.interrupt();
                collectExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }

        }
    }

    private class CollectThread extends Thread {
        @Override
        public void run() {
            if(!isOkay) {
                Thread.currentThread().interrupt();
            }
            try {
                System.out.println("COLLECT");
                updateLiveList();
            } catch (Exception e) {
                isOkay = false;
                logger.error("CollectException: "+e.getMessage()+"|"+e.getCause().getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

}
