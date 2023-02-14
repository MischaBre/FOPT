package gui.mvp.training;

import java.util.LinkedHashMap;
import java.util.Map;

public class Model {

    private Map<String, TrainingUnit> data;

    public Model() {
        data = new LinkedHashMap<>();
    }

    public Map<String, TrainingUnit> getData() {
        return data;
    }
    public void setData(Map<String, TrainingUnit> data) {
        this.data = data;
    }

    public void addTrainingUnit(TrainingUnit t) {
        if (data.containsKey(t.getMarker())) {
            throw new IllegalArgumentException();
        }
        data.put(t.getMarker(), t);
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
        data.forEach((k, v) -> s.append(k).append(": ").append(v.getTime()).append(" - "));
        return s.toString();
    }
}
