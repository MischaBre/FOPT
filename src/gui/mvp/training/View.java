package gui.mvp.training;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.util.HashMap;
import java.util.Map;

public class View {

    private final TrainingPresenter presenter;

    private final VBox root;
    private final ListView<String> trainingListView = new ListView<>();
    private final Map<String, Label> labels = new HashMap<>();
    private final Button bAdd = new Button("Neue Trainingseinheit hinzufügen");
    private final Button bDel = new Button("Trainingseinheit löschen");

    public View(TrainingPresenter presenter) {
        this.presenter = presenter;
        this.root = new VBox(10);
        initUI();
        setupListeners();
    }

    public void initUI() {

        trainingListView.setId("overviewList");
        trainingListView.setMinWidth(150);

        labels.put("mL", new Label());
        labels.get("mL").setId("markerLabel");
        labels.put("dL", new Label());
        labels.get("dL").setId("distanceLabel");
        labels.put("tL", new Label());
        labels.get("tL").setId("timeLabel");
        labels.put("mSL", new Label());
        labels.get("mSL").setId("meanSpeedLabel");
        labels.put("errML", new Label());
        labels.get("errML").setId("errMsgLabel");

        VBox vb2 = new VBox(10);
        vb2.getChildren().add(new HBox(new Label("Kennung: "), labels.get("mL")));
        vb2.getChildren().add(new HBox(new Label("Entfernung [km]: "), labels.get("dL")));
        vb2.getChildren().add(new HBox(new Label("Zeit [Minuten]: "), labels.get("tL")));
        vb2.getChildren().add(new HBox(new Label("Durchschnittsgeschwindigkeit [km/h]: "), labels.get("mSL")));

        HBox hb1 = new HBox(10);
        hb1.getChildren().add(trainingListView);
        hb1.getChildren().add(vb2);

        HBox hb2 = new HBox(10);
        hb2.getChildren().add(bAdd);
        hb2.getChildren().add(bDel);

        VBox vb1 = new VBox(10);
        vb1.getChildren().add(hb1);
        vb1.getChildren().add(hb2);

        root.setMinWidth(600);
        root.getChildren().add(vb1);
    }

    public void setupListeners() {
        trainingListView.setOnMouseClicked(e -> updateListSelection());
        bAdd.setOnAction(e -> {
            TrainingUnit t = showDialog();
        });
        bDel.setOnAction(e -> {
            deleteListSelection();
            updateListSelection();
        });
    }

    public void setupListView() {
        trainingListView.setItems(presenter.getTrainingList());
    }

    public Pane getUI() {
        return root;
    }

    public TrainingUnit showDialog() {
        EditorDialog ed = new EditorDialog(presenter);
        ed.initModality(Modality.APPLICATION_MODAL);
        ed.showAndWait();
        String[] blub = ed.getValues();
        for (String s : blub) {
            System.out.println(s);
        }
        return null;
    }

    public void updateListSelection() {
        String selectedUnit = trainingListView.getSelectionModel().getSelectedItem();
        presenter.select(selectedUnit);
    }

    public void deleteListSelection() {
        String selectedUnit = trainingListView.getSelectionModel().getSelectedItem();
        presenter.delete(selectedUnit);
    }

    public void updateLabels(boolean empty, String name, Float d, Float t, Float v) {
        if (!empty) {
            labels.get("mL").setText(name);
            labels.get("dL").setText(String.format("%.1f", d));
            labels.get("tL").setText(String.format("%.1f", t));
            labels.get("mSL").setText(String.format("%.1f", v));
        } else {
            labels.get("mL").setText("");
            labels.get("dL").setText("");
            labels.get("tL").setText("");
            labels.get("mSL").setText("");
        }
    }

}
