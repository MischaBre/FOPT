package clock;

public class ClockPresenter {
    private Clock clock;
    private ClockView view;
    private ClockTicker ticker;

    public void setModelAndView(Clock clock, ClockView view) {
        this.clock = clock;
        this.view = view;
    }

    public void start() {
        if (ticker == null) {
            ticker = new ClockTicker(clock, view);
        }
    }

    public void stop() {
        if (ticker != null) {
            ticker.interrupt();
            ticker = null;
        }
    }

    public void reset() {
        clock.setStartTime();
        view.updateTime(clock.getCurrentTime());
    }
}
