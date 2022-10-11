package zehnDrei;

import java.util.Arrays;
import java.util.Random;

public class AlterMessageSendReciever extends Thread {
    private final MessageQueue msgQ1;
    private final MessageQueue msgQ2;
    private final int speed;

    public AlterMessageSendReciever(String name, MessageQueue msgQ1, MessageQueue msgQ2, int speed) {
        super(name);
        this.msgQ1 = msgQ1;
        this.msgQ2 = msgQ2;
        this.speed = speed;
        start();
    }

    @Override
    public void run() {
        while (true) {
            byte[] sendBytes = new byte[10];
            new Random().nextBytes(sendBytes);
            msgQ1.send(sendBytes);
            System.out.println(System.nanoTime() / 1_000_000 + " - " + getName() + ": Sent " + Arrays.toString(sendBytes));
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.nanoTime() / 1_000_000 + " - " + getName() + ": Recieving " + Arrays.toString(msgQ2.recieve()));
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
