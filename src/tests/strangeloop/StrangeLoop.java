package tests.strangeloop;

public class StrangeLoop extends Thread
{
    private SimpleIntegerValue data;

    public StrangeLoop(SimpleIntegerValue data)
    {
        this.data = data;
    }

    public void run()
    {
        for(int i = 1; i <= 10; i++)
        {
            int local = data.getValue();
            local++;
            data.setValue(local);
        }
    }

    public static void main(String[] args)
    {
        SimpleIntegerValue data = new SimpleIntegerValue();
        StrangeLoop t1 = new StrangeLoop(data);
        StrangeLoop t2 = new StrangeLoop(data);
        t1.start();
        t2.start();
        try
        {
            t1.join();
            t2.join();
        }
        catch(InterruptedException e)
        {
        }
        System.out.println("Final value: " + data.getValue());
    }
}