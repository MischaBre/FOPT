package gui.mvp.training;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager {
    private final List<UndoRedoableAction> actionList = new ArrayList<>();
    private int currentPosition;

    public UndoRedoManager() {
    }

    public void addAction(UndoRedoableAction action) {
        for (int i = actionList.size() - 1; i >= currentPosition; i--) {
            actionList.remove(i);
        }
        actionList.add(action);
        currentPosition++;
    }

    public void undo() {
        if (currentPosition > 0) actionList.get(--currentPosition).undo();
    }

    public void redo() {
        if (currentPosition < actionList.size()) actionList.get(currentPosition++).redo();
    }
}
