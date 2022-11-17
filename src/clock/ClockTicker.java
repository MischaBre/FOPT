package clock;

import javafx.application.Platform;

public class ClockTicker extends Thread {
    private final static long UPDATE_INTERVAL = 10;
    private Clock clock;
    private ClockView view;

    public ClockTicker(Clock clock, ClockView view) {
        this.clock = clock;
        this.view = view;
        setDaemon(true);
        start();
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                Platform.runLater(() -> view.updateTime(clock.getCurrentTime()));
                Thread.sleep(UPDATE_INTERVAL);
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }
}
