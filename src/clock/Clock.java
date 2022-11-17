package clock;

public class Clock {
    private long startTime;

    public Clock() {
        startTime = System.currentTimeMillis();
    }

    public long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }

    public void setStartTime() {
        startTime = System.currentTimeMillis();
    }
}
