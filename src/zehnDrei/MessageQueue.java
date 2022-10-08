package zehnDrei;

public class MessageQueue {
    private byte[][] msgQueue = null;
    private int qsize = 0;
    private int head = 0;
    private int tail = 0;
    public MessageQueue(int capacity) {
        // Abprüfen von capacity <= 0 ausgelassen ...
        msgQueue = new byte[capacity][];
    }

    public int getCapacity() {
        return msgQueue.length;
    }

    public synchronized void send(byte[] msg) {
        while(qsize == msgQueue.length) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        msgQueue[tail] = new byte[msg.length];
        for(int i = 0; i < msg.length; i++) {
            msgQueue[tail][i] = msg[i];
        }
        qsize++;
        tail++;
        if(tail == msgQueue.length) {
            tail = 0; }
        notifyAll(); // nicht notify
    }

    public synchronized byte[] recieve() {
        while(qsize == 0) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        byte[] result = msgQueue[head];
        msgQueue[head] = null;
        qsize--;
        head++;
        if(head == msgQueue.length) {
            head = 0; }
        notifyAll(); // nicht notify
        return result;
    }

    public static void main(String[] args) {
        MessageQueue mQ1 = new MessageQueue(10);
        MessageQueue mQ2 = new MessageQueue(10);

        AlterMessageSendReciever amsr1 = new AlterMessageSendReciever("A1", mQ1, mQ2);
        AlterMessageSendReciever amsr2 = new AlterMessageSendReciever("A2", mQ2, mQ1);
    }
}