package tests.strangeloop;

class SimpleIntegerValue
{
    private int value;

    public synchronized int getValue()
    {
        return value;
    }

    public synchronized void setValue(int value)
    {
        this.value = value;
        notify();
    }
}