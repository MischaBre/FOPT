package gui.mvp.training;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;

import java.util.Map;

public class Presenter {
    protected Model model;
    protected View view;
    private final ObservableList<String> trainingList = FXCollections.observableArrayList();
    //private final UndoRedoManager undoRedoManager = new UndoRedoManager();
    public Presenter() {
    }

    public void setupModelView(Model m, View v) {
        this.model = m;
        this.view = v;

        model.addTrainingUnit(new TrainingUnit("a", 1, 2));
        model.addTrainingUnit(new TrainingUnit("b", 2, 3));
        model.addTrainingUnit(new TrainingUnit("c", 3, 4));

        trainingList.addAll(model.getAllMarkers());
        view.setupListView();
    }

    public ObservableList<String> getTrainingList() {
        return trainingList;
    }

    public void showEditorDialog() {
        EditorDialog ed = new EditorDialog();
        ed.initModality(Modality.APPLICATION_MODAL);
        ed.setPresenter(this);
        ed.showAndWait();
    }

    public void add(TrainingUnit t) {
        if (t.getMarker().isEmpty()) {
            throw new IllegalArgumentException();
        }
        //Map<String, TrainingUnit> oldData = model.getData();
        model.addTrainingUnit(t);
        //undoRedoManager.addAction(new UndoRedoAction(oldData, model.getData(), model, this));
        trainingList.add(t.getMarker());
    }

    public void select(String s) {
        if (s == null) {
            view.updateEmptyLabels();
        } else {
            TrainingUnit t = model.getTrainingUnit(s);
            view.updateLabels(t.getMarker(), t.getDistance(), t.getTime(), t.getMeanSpeed());
        }
    }

    public void delete(String s) {
        if (s != null) {
            trainingList.remove(s);
            model.removeTrainingUnit(s);
        }
    }
}
