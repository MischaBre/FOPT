package gui.mvp.training;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorDialog extends Stage {

    private String name;
    private String distance;
    private String time;
    private TrainingPresenter presenter;

    private final Button bAdd = new Button("Hinzufügen");
    private final Button bCancel = new Button("Abbrechen");
    private final TextField tfMarker = new TextField();
    private final TextField tfDistance = new TextField();
    private final TextField tfTime = new TextField();

    public EditorDialog(TrainingPresenter presenter) {
        this.presenter = presenter;
        initUI();
        setEvents();
    }

    private void initUI() {
        HBox hb1 = new HBox(new Label("Kennung (nicht leer): "), tfMarker);
        HBox hb2 = new HBox(new Label("Entfernung (in km): "), tfDistance);
        HBox hb3 = new HBox(new Label("Zeit (in Minuten): "), tfTime);
        HBox hb4 = new HBox(bAdd, bCancel);

        VBox root = new VBox(10);
        root.getChildren().add(hb1);
        root.getChildren().add(hb2);
        root.getChildren().add(hb3);
        root.getChildren().add(hb4);
        Scene scene = new Scene(root, 300, 150);
        setScene(scene);
    }

    private void setEvents() {
        bAdd.setOnAction(e -> {
            String n = tfMarker.textProperty().get();
            String d = tfDistance.textProperty().get();
            String t = tfTime.textProperty().get();

            try {
                setVariables(n, d, t);
                close();
            } catch (IllegalArgumentException ex) {

            }
        });
        bCancel.setOnAction(e -> close());
    }

    public String[] getValues() {
        return new String[] {name, distance, time};
    }

    private void setVariables(String n, String d, String t) {
        if (n.isEmpty()) {
            throw new IllegalArgumentException();
        }
        name = n;
        distance = String.valueOf(Float.parseFloat(d));
        time = String.valueOf(Float.parseFloat(t));
    }

}
