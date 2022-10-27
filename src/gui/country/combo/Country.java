package gui.country.combo;

import java.text.NumberFormat;
import java.util.Locale;

public class Country
{
    private String name;
    private String capital;
    private long people;
    private long area;
    public Country(String name, String capital, long people, long area)
    {
        this.name = name;
        this.capital = capital;
        this.people = people;
        this.area = area;
    }
    public String getName()
    {
        return name;
    }
    public String getCapital()
    {
        return capital;
    }
    public long getPeople()
    {
        return people;
    }
    public long getArea()
    {
        return area;
    }
    public String getBevDichte()
    {
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(Math.round((double) people / area));
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCapital(String capital)
    {
        this.capital = capital;
    }

    public void setPeople(long people)
    {
        this.people = people;
    }

    public void setArea(long area)
    {
        this.area = area;
    }
    @Override
    public String toString()
    {
        return this.name;
    }
}

