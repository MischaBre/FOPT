package praktikum.tag2.mvp;

public class Gegenstand {
    private final String name;
    private final double gewicht;
    private boolean selected;

    public Gegenstand(String name, double gewicht, boolean selected) {
        this.name = name;
        this.gewicht = gewicht;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }


    public double getGewicht() {
        return gewicht;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f)",name ,gewicht);
    }
}
