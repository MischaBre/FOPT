package gui.mvp.training;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TrainingPresenter {
    protected Model model;
    protected View view;

    private final ObservableList<String> trainingList = FXCollections.observableArrayList();

    public TrainingPresenter() {
    }

    public void setupModelView(Model model, View view) {
        this.model = model;
        this.view = view;
        /*
        model.addTraining(new TrainingUnit("a", 1, 2));
        model.addTraining(new TrainingUnit("b", 2,3));
        model.addTraining(new TrainingUnit("c", 3,4));
         */

        trainingList.addAll(model.getAllMarkers());
        view.setupListView();
    }

    public ObservableList<String> getTrainingList() {
        return trainingList;
    }

    public void select(String s) {
        if (s == null) {
            sendEmptyUpdateView();
        } else {
            TrainingUnit t = model.getTrainingUnit(s);
            view.updateLabels(false, t.getName(), t.getTrDistance(), t.getTrTime(), t.getMeanSpeed());
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
