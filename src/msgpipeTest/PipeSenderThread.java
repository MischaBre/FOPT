package msgpipeTest;
import java.util.Arrays;
import java.util.Random;

public class PipeSenderThread extends Thread {
    private int bufferSize;
    private int speed;
    private Pipe pipe;

    public PipeSenderThread(String name, Pipe pipe, int size, int speed) {
        super(name);
        this.pipe = pipe;
        bufferSize = size;
        this.speed = speed;
        start();
    }

    public void run() {
        byte[] sendBuffer;
        while (true) {
            int lenSend = Math.min(bufferSize, (int) (Math.random() * bufferSize));
            sendBuffer = new byte[lenSend];
            new Random().nextBytes(sendBuffer);
            System.out.println(getName() + ": Sending " + lenSend + " bytes: " + Arrays.toString(sendBuffer));
            pipe.send(sendBuffer);
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
