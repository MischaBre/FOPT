package gui.mvp.training;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Presenter {
    protected Model model;
    protected View view;

    private final ObservableList<String> trainingList = FXCollections.observableArrayList();

    public Presenter() {
    }

    public void setupModelView(Model model, View view) {
        this.model = model;
        this.view = view;

        model.addTrainingUnit(new TrainingUnit("a", 1, 2));
        model.addTrainingUnit(new TrainingUnit("b", 2,3));
        model.addTrainingUnit(new TrainingUnit("c", 3,4));

        trainingList.addAll(model.getAllMarkers());
        view.setupListView();
    }

    public ObservableList<String> getTrainingList() {
        return trainingList;
    }

    public void add(TrainingUnit t) {
        if (t != null) {
            try {
                model.addTrainingUnit(t);
                trainingList.add(t.getMarker());
            } catch (IllegalArgumentException e) {

            }
        }
    }

    public void select(String s) {
        if (s == null) {
            sendEmptyUpdateView();
        } else {
            TrainingUnit t = model.getTrainingUnit(s);
            view.updateLabels(false, t.getMarker(), t.getDistance(), t.getTime(), t.getMeanSpeed());
        }
    }

    public void delete(String s) {
        if (s != null) {
            trainingList.remove(s);
            model.removeTrainingUnit(s);
        }
    }

    private void sendEmptyUpdateView() {
        view.updateLabels(true, "", 0f,0f,0f);
    }
}
