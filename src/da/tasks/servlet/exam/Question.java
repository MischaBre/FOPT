package da.tasks.servlet.exam;

public enum Question
{
    Q1,
    Q2;

    public String toString()
    {
        return this.name().toLowerCase();
    }
}
