package pp.synchstacksem;

import java.util.Random;

public class SynchStackTester extends Thread
{
    private boolean isSender;
    private SynchStack stack;

    public SynchStackTester(String name, SynchStack stack, boolean isSender)
    {
        super(name);
        this.stack = stack;
        this.isSender = isSender;
        start();
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (isSender)
            {
                int i = (int) (Math.random() * 10);
                stack.push(i);
            }
            else
            {
                stack.pop();
            }
            try
            {
                sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
