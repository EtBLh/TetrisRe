package com.processer;

import com.service.Service;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class processor {

    private Timer process;
    private Service service;

    public processor(Service service) {
        this.service = service;
        process = new Timer();
    }

    /**
     * stop the process
     */
    public void stop() {
        process.cancel();
    }

    /**
     * start the process
     */
    public void start() {
        process.schedule(new task(), 0, 1000);
    }

    /**
     *
     * update the duration time
     * @param period
     */
    public void update(int period) {
        //TODO
    }


    class task extends TimerTask {
        @Override
        public void run() {
            Platform.runLater(service::ActDown);
        }
    }

}
