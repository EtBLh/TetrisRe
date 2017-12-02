package com.processer;

import com.service.Service;
import javafx.application.Platform;

public class processor {

    private Thread process;
    private Service service;
    private int duration = 1000;
    private boolean isProcessing = true;

    public processor(Service service) {
        this.service = service;
        process = new Thread(new task());
    }

    /**
     * stop the process
     */
    public void stop() {
        this.isProcessing = false;
    }

    /**
     * start the process
     */
    public void start() {
        process.start();
    }

    /**
     * update the duration time
     */
    public void update(int duration) {
        this.duration = duration;
    }


    class task implements Runnable {
        @Override
        public void run() {
            try {
                loop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(isProcessing)run();
            }
        }

        private void loop() throws InterruptedException {
            Platform.runLater(service::ActDown);
            Thread.sleep(duration);
        }
    }
}
