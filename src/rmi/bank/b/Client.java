package rmi.bank.b;

import java.rmi.Naming;

public class Client {
    public Client(int port, String[] command) {
        int num = Integer.parseInt(command[1]);
        if (num < 0 || num > 99 ) {
            throw new IllegalArgumentException("Möp");
        }
        switch (command[0]) {
            case "l": {
                readBalance(port, Integer.parseInt(command[1]));
                break;
            }
            case "s": {
                changeBalance(port, Integer.parseInt(command[1]), Double.parseDouble(command[2]));
                break;
            }
            default:
                break;
        }
    }
    private void readBalance(int port, int num) {
        try {
            Account account = (Account) Naming.lookup("rmi://localhost:" + port + "/Konto" + num);
            System.out.println(account.readBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeBalance(int port, int num, double value) {
        try {
            Account account = (Account) Naming.lookup("rmi://localhost:" + port + "/Konto" + num);
            account.changeBalance(value);
            // System.out.println(account.readBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client cl = new Client(1099, new String[] {"l", "20", "-1.7"});
    }
}
