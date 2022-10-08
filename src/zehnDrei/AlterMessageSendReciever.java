package zehnDrei;

import java.util.Arrays;
import java.util.Random;

public class AlterMessageSendReciever extends Thread {
    private MessageQueue msgQ1;
    private MessageQueue msgQ2;

    public AlterMessageSendReciever(String name, MessageQueue msgQ1, MessageQueue msgQ2) {
        super(name);
        this.msgQ1 = msgQ1;
        this.msgQ2 = msgQ2;
        start();
    }

    @Override
    public void run() {
        while (true) {
            byte[] sendBytes = new byte[10];
            new Random().nextBytes(sendBytes);
            System.out.println(getName() + ": Sending " + Arrays.toString(sendBytes));
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msgQ1.send(sendBytes);
            System.out.println(getName() + ": Recieving " + Arrays.toString(msgQ2.recieve()));
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
