package tests.fopt1;

public class Auto extends Thread {
    private Ampel[] ampeln;

    public Auto(Ampel[] ampeln) {
        super();
        this.ampeln = ampeln;
        start();
    }

    public void run() {
        for (Ampel ampel : ampeln) {
            ampel.passieren();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
