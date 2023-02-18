package praktikum.tag2.mvp;

import javafx.collections.transformation.SortedList;

import java.util.*;

public class Model {
    private final List<Gegenstand> rucksack = new ArrayList<>();
    private final double maxWeight = 10;

    public Model() {
        // init Gegenstände
        this.rucksack.add(new Gegenstand("Waschzeug", 1.2, false));
        this.rucksack.add(new Gegenstand("Ersatzkleidung", 3.7, false));
        this.rucksack.add(new Gegenstand("Bücher", 3.3, false));
        this.rucksack.add(new Gegenstand("Medikamente", 0.2, false));
        this.rucksack.add(new Gegenstand("Essenspaket 1", 1.5, false));
        this.rucksack.add(new Gegenstand("Essenspaket 2", 2.4, false));
        this.rucksack.add(new Gegenstand("Getränkemix 1", 4.7, false));
        this.rucksack.add(new Gegenstand("Getränkemix 2", 2.9, false));
    }

    private List<Gegenstand> getGegenstaende(boolean val) {
        return rucksack.stream().filter(g -> g.isSelected() == val).toList();
    }
    private List<Gegenstand> getGegenstaende() {
        return rucksack.stream().toList();
    }

    public String[] getAllItemNames() {
        List<String> result = new ArrayList<>();
        getGegenstaende().forEach(g -> result.add(g.getName()));
        return result.toArray(new String[0]);
    }

    public double getAllItemWeights() {
        double result = 0;
        return getGegenstaende().stream().mapToDouble(Gegenstand::getGewicht).sum();
    }

    public String[] getEnabledItems() {
        List<String> result = new ArrayList<>();
        getGegenstaende(false).forEach(g -> result.add(g.getName()));
        return result.toArray(new String[0]);
    }

    public double getSumOfUsedWeights() {
        double result = 0;
        return getGegenstaende(true).stream().mapToDouble(Gegenstand::getGewicht).sum();
    }

    public Gegenstand getItem(int index) {
        return rucksack.get(index);
    }

    public void setSelected(String name, boolean selected) {
        for (Gegenstand g : rucksack) {
            if (g.getName().equals(name)) {
                g.setSelected(selected);
            }
        }
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
