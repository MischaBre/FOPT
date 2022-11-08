package gui.mvp.training;

import java.util.Map;
import java.util.TreeMap;

public class Model {

    private final Map<String, TrainingUnit> data;

    public Model() {
        data = new TreeMap<>();
    }

    public void addTraining(TrainingUnit t) {
        if (data.containsKey(t.getName())) {
            throw new IllegalArgumentException();
        }
        data.put(t.getName(), t);
    }

    public void removeTrainingUnit(String name) {
        data.remove(name);
    }

    public TrainingUnit getTrainingUnit(String name) {
        return data.get(name);
    }

    public String[] getAllMarkers() {
        return data.keySet().toArray(new String[0]);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        data.forEach((k, v) -> s.append(k + ": " + v.getTrTime() + " - "));
        return s.toString();
    }
}
