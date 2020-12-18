package server.controller;

import bigtennis.controller.BigTennisDataController;
import bigtennis.dao.BigTennisSeleniumDataProvider;
import server.exception.SeleniumInitException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import tabletennis.controller.TableTennisDataController;
import tabletennis.dao.TableTennisSeleniumDataProvider;

import java.util.Timer;
import java.util.TimerTask;

public class CollectingController implements Runnable {

    private static final CollectingController instance = new CollectingController();
    private static final Logger logger = LogManager.getLogger(CollectingController.class);
    private final BigTennisSeleniumDataProvider bigTennisSeleniumDataProvider = new BigTennisSeleniumDataProvider();
    private final TableTennisSeleniumDataProvider tableTennisSeleniumDataProvider = new TableTennisSeleniumDataProvider();
    private final BigTennisDataController bigTennisDataController = BigTennisDataController.getInstance();
    private final TableTennisDataController tableTennisDataController = TableTennisDataController.getInstance();
    private final SeleniumTask task = new SeleniumTask();

    public static CollectingController getInstance() {
        return instance;
    }

    private CollectingController() {
    }


    public void turnOnCollecting() {
        task.turnOnCollecting();
    }

    public void turnOffCollecting() {
        task.turnOffCollecting();
    }

    @Override
    public void run() {
        CollectingController collectingController = new CollectingController();
        collectingController.turnOnCollecting();
    }

    public class SeleniumTask extends TimerTask {

        Timer timer = new Timer();

        public void turnOnCollecting() {
            timer.schedule(task, 0, 20 * 60 * 1000);
        }

        public void turnOffCollecting() {
            timer.purge();
        }

        private void loadTableTennis() throws SeleniumInitException, InterruptedException {
            tableTennisSeleniumDataProvider.setLeagues(tableTennisDataController.getLeagues());
            tableTennisDataController.insertMatches(tableTennisSeleniumDataProvider.getNewMatches());
        }

        private void loadtableTennisFor(int monthQuantity) throws SeleniumInitException, InterruptedException {
            tableTennisSeleniumDataProvider.getMatchesByDate(monthQuantity);
        }

        private void loadBigTennis() throws SeleniumInitException, InterruptedException {
            bigTennisDataController.insertMatches(bigTennisSeleniumDataProvider.getNewMatches());
        }

        private void loadBigTableTennisFor(int monthQuantity) throws SeleniumInitException, InterruptedException {
            bigTennisSeleniumDataProvider.getMatchesByDate(monthQuantity);
        }

        @Override
        public void run() {
            try {
                loadBigTennis();
                loadTableTennis();
                //loadBigTableTennisFor(4);
            } catch (SeleniumInitException seleniumInitException) {
                logger.error("Хром не был запущен. Ожидание следующей итерации", seleniumInitException);
            } catch (InterruptedException interruptedException) {
                logger.error("Ошибка прерывания потока", interruptedException);
            }
        }
    }

}
