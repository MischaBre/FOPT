package neunDrei;

import java.util.concurrent.Semaphore;

public class AlterThreads {
    public static void main(String[] args) {
        Semaphore mySem1 = new Semaphore(1);
        Semaphore mySem2 = new Semaphore(0);
        myThread mT1 = new myThread("Eine", mySem1, mySem2);
        myThread mT2 = new myThread("Andere", mySem2, mySem1);
    }
}
