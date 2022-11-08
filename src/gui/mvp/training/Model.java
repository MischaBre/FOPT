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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        data.forEach((k, v) -> s.append(k).append(": ").append(v.getTrTime()).append(" - "));
        return s.toString();
    }
}
