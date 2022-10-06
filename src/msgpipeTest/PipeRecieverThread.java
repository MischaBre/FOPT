package msgpipeTest;
import java.util.Arrays;

public class PipeRecieverThread extends Thread {
    private int bufferSize;
    private int speed;
    private Pipe pipe;

    public PipeRecieverThread(String name, Pipe pipe, int size, int speed) {
        super(name);
        this.pipe = pipe;
        this.speed = speed;
        bufferSize = size;
        start();
    }

    public void run() {
        while (true) {
            byte[] recieveBuffer = pipe.receive(bufferSize);
            System.out.println(getName() + ": Recieving " + recieveBuffer.length + " bytes: " + Arrays.toString(recieveBuffer));
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
