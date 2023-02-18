package praktikum.tag3.rmiexport;


import java.io.Serializable;

public class Data implements Serializable, IData {
    private int number = 7;

    public int getNumber() {
        System.out.println("get " + number);
        return number;
    }

    public void setNumber(int number) {
        System.out.println("set " + number);
        this.number = number;
    }
}
