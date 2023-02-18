package praktikum.tag2.mvp;

import javafx.scene.layout.Pane;

public class View {
    private final Pane root = new Pane();


    public View() {
        initUI();
    }

    private void initUI() {

    }

    public Pane getRoot() {
        return root;
    }
}
