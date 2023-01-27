package socket.tcp.scan;

import java.net.Socket;


public class PortScanner
{
    private static final int TIMEOUT = 2000;

    private String target;
    private int untereGrenze;
    private int obereGrenze;


    public PortScanner(String target, int untereGrenze, int obereGrenze)
    {
        this.target = target;
        this.untereGrenze = untereGrenze;
        this.obereGrenze = obereGrenze;
        startWorkers();
    }

    protected static void scanPort(String target, int port)
    {
        try(Socket socket = new Socket(target, port))
        {
            System.out.println(Thread.currentThread().getName() + ": Port " + port + " ist offen");
        }
        catch (Exception e)
        {
            // e.printStackTrace();
        }
    }

    private void startWorkers()
    {
        System.out.println(Thread.currentThread().getName() + " started");
        for (int i = untereGrenze; i <= obereGrenze; i++)
        {
            int finalI = i;
            Thread t = new Thread(() ->
            {
                PortScanner.scanPort(target, finalI);
            });
            t.start();
        }
    }

    public static void main(String[] args)
    {
        PortScanner ps = new PortScanner(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }
}
