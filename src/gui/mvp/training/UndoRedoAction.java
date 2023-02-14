package gui.mvp.training;

import java.util.Map;

public class UndoRedoAction implements UndoRedoableAction {
    private Map<String, TrainingUnit> oldUnit, newUnit;
    private Model model;
    private Presenter presenter;

    public UndoRedoAction(Map<String, TrainingUnit> oldUnit, Map<String, TrainingUnit> newUnit, Model model, Presenter presenter) {
        this.oldUnit = oldUnit;
        this.newUnit = newUnit;
        this.model = model;
        this.presenter = presenter;
    }

    @Override
    public void redo() {
        model.setData(newUnit);
    }

    @Override
    public void undo() {
        model.setData(oldUnit);
    }
}
