package gui.mvp.training;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorDialog extends Stage {
    private String name;
    private Float distance;
    private Float time;
    private Presenter presenter;

    private final Button bAdd = new Button("Hinzufügen");
    private final Button bCancel = new Button("Abbrechen");
    private final TextField tfMarker = new TextField();
    private final TextField tfDistance = new TextField();
    private final TextField tfTime = new TextField();
    private final Label errLabel = new Label();

    public EditorDialog() {
        initUI();
        setEvents();
    }

    private void initUI() {
        tfMarker.setId("markerTF");
        tfDistance.setId("distanceTF");
        tfTime.setId("timeTF");
        errLabel.setId("errMsgLabel");

        HBox hb1 = new HBox(new Label("Kennung (nicht leer): "), tfMarker);
        HBox hb2 = new HBox(new Label("Entfernung (in km): "), tfDistance);
        HBox hb3 = new HBox(new Label("Zeit (in Minuten): "), tfTime);
        HBox hb4 = new HBox(bAdd, bCancel);
        HBox hb5 = new HBox(errLabel);

        VBox root = new VBox(10);
        root.getChildren().add(hb1);
        root.getChildren().add(hb2);
        root.getChildren().add(hb3);
        root.getChildren().add(hb4);
        root.getChildren().add(hb5);
        Scene scene = new Scene(root, 300, 150);
        setScene(scene);
    }

    private void setEvents() {
        bAdd.setOnAction(e -> onClickAdd());
        bCancel.setOnAction(e -> close());
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    private void onClickAdd() {
        String n = tfMarker.textProperty().get();
        String d = tfDistance.textProperty().get();
        String t = tfTime.textProperty().get();
        try {
            name = n;
            distance = Float.parseFloat(d);
            time = Float.parseFloat(t);
            presenter.add(new TrainingUnit(name, distance, time));
            close();

        } catch (IllegalArgumentException ex) {
            Platform.runLater(() -> {
                if (n.isEmpty()) {
                    errLabel.setText("Kennung: ungültige Eingabe");
                } else if (distance == null || distance < 0.0f) {
                    errLabel.setText("Entfernung: ungültige Eingabe");
                } else if (time == null || time < 0.0f) {
                    errLabel.setText("Zeit: ungültige Eingabe");
                } else {
                    errLabel.setText("Kennung: existiert schon");
                }
            });
        }
    }
}
