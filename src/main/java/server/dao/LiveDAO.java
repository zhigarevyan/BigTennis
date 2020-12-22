package server.dao;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import server.entity.LiveMatchBuilder;
import server.entity.LiveMatchList;
import tabletennis.dao.WebElementProvider;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


public class LiveDAO implements Runnable {

    private static final Logger logger = Logger.getLogger(LiveDAO.class);

    private static final ChromeOptions options = initOptions();
    private final String URL;
    private ChromeDriver liveChromeDriver;

    protected WebElementProvider webElementProvider;
    protected final static LiveMatchBuilder liveMatchBuilder = new LiveMatchBuilder();

    private UpdateTask updateTask = new UpdateTask();
    private CollectTask collectTask = new CollectTask();

    private Timer updateTimer = new Timer();
    private Timer collectTimer = new Timer();

    public LiveDAO(String URL) {
        this.URL = URL;
    }

    public void updateLiveList() {
    }

    @Override
    public void run() {
        liveChromeDriver = new ChromeDriver(options);
        webElementProvider = new WebElementProvider(liveChromeDriver);

        updateTimer.schedule(updateTask,0,1000*60*10);
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

    private class UpdateTask extends TimerTask {
        @Override
        public void run() {
                collectTimer.cancel();
                collectTimer.purge();
                collectTimer = new Timer();

                initPage();
                webElementProvider.waitUntilLiveLoaded();
                collectTimer.schedule(collectTask,0,10 * 1000);
        }
    }

    private class CollectTask extends TimerTask {
        @Override
        public void run() {
            updateLiveList();
        }
    }

}
