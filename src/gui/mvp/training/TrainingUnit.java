package gui.mvp.training;

public class TrainingUnit implements Comparable<TrainingUnit> {
    private final String name;
    private final float trDistance;
    private final float trTime;

    public TrainingUnit(String name, float trDistance, float trTime) {
        this.name = name;
        this.trDistance = trDistance;
        this.trTime = trTime;
    }

    @Override
    public int compareTo(TrainingUnit o) {
        return this.name.compareTo(o.name);
    }

    public String getMarker() {
        return name;
    }

    public float getDistance() {
        return trDistance;
    }

    public float getTime() {
        return trTime;
    }

    public float getMeanSpeed() {
        return trDistance / trTime * 60;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
