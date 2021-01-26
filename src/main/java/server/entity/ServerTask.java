package server.entity;

import java.util.concurrent.Future;

public class ServerTask {

    private Future future;
    private Runnable task;

    public Future<StatusName> getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }
}
