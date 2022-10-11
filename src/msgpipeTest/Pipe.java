package msgpipeTest;

public class Pipe {

    private final byte[] buffer;
    private int bsize = 0;
    private int head = 0;
    private int tail = 0;

    public Pipe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("zu kleine Kapazität");
        }
        buffer = new byte[capacity];
    }

    public int getCapacity() {
        return buffer.length;
    }

    public synchronized void send(byte[] msg) {
        // check if send all in once or step by step
        if (msg.length <= buffer.length) {
            // wait if not enough space
            while (msg.length > buffer.length - bsize) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": Pipe full! --");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // send data
            for (byte b : msg) {
                buffer[tail++] = b;
                if (tail == buffer.length) {
                    tail = 0;
                }
            }
            // note how many bytes in buffer
            bsize += msg.length;
            notifyAll();
        } else {    // msg.length > buffer.length
            int offset = 0;
            int stillToSend = msg.length;
            // repeat until all data sent
            while (stillToSend > 0) {
                // wait if no space available
                while (bsize == buffer.length) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Pipe full! --");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // send rest or only for available space
                int sendNow = Math.min(buffer.length - bsize, stillToSend);
                // send data
                for (int i = 0; i < sendNow; i++) {
                    buffer[tail++] = msg[offset++];
                    if (tail == buffer.length) {
                        tail = 0;
                    }
                }
                // note how many bytes in buffer;
                bsize += sendNow;
                stillToSend -= sendNow;
                notifyAll();
            }
        }
    }

    public synchronized byte[] receive(int numBytes) {
        // wait, when no data
        while (bsize == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Pipe empty!");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // get numBytes data or less (all there is)
        int bytesToReceive = Math.min(numBytes, bsize);
        byte[] result = new byte[bytesToReceive];
        // get data
        for (int i = 0; i < bytesToReceive; i++) {
            result[i] = buffer[head++];
            if (head == buffer.length) {
                head = 0;
            }
        }
        // not how many data taken out of buffer
        bsize -= bytesToReceive;
        notifyAll();
        return result;
    }

    public static void main(String[] args) {
        int CAPACITY = 30;
        Pipe pipe = new Pipe(10);
        PipeSenderThread pipeSender = new PipeSenderThread("s1", pipe, CAPACITY, 20);
        PipeRecieverThread pipeRecieverThread = new PipeRecieverThread("r1", pipe, CAPACITY, 40);
    }
}
