package praktikum.tag2.tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewView {
    private final Pane root;
    private Stage addStage, editStage, editAllStage;
    private final ObservableList<StringDoublePair> dataList;

    public TableViewView() {
        root = new Pane();
        dataList = FXCollections.observableArrayList();
        initUI();
    }

    private void initUI() {
        // setup TableView with dataList
        TableView<StringDoublePair> table = new TableView<>(dataList);

        // setup Columns and add them to table
        TableColumn<StringDoublePair, String> groupCol = new TableColumn<>("Entgeldgruppe");
        groupCol.setMinWidth(80);
        groupCol.setCellValueFactory(
                new PropertyValueFactory<StringDoublePair, String>("string")
        );
        TableColumn<StringDoublePair, Double> salaryCol = new TableColumn<>("Gehalt");
        salaryCol.setMinWidth(150);
        salaryCol.setCellValueFactory(
                new PropertyValueFactory<StringDoublePair, Double>("double")
        );
        table.getColumns().addAll(groupCol, salaryCol);

        // mock data
        dataList.add(new StringDoublePair("blub", 1.23));
        dataList.add(new StringDoublePair("blub", 1.24));
        dataList.add(new StringDoublePair("blub", 1.25));

        table.setMinWidth(400);
        table.setMinHeight(400);


        Button[] buttons = new Button[] {
                new Button("Hinzufügen"),
                new Button("Ändern"),
                new Button("Löschen"),
                new Button("Alle anpassen")
        };

        HBox hbox = new HBox();
        hbox.getChildren().addAll(buttons);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, hbox);
        root.getChildren().addAll(vbox);


        // add actionEvents
        buttons[0].setOnAction((e) -> {
        // add
            if (addStage != null) {
                return;
            }
            showAddDialogue();
            addStage = null;
        });
        buttons[1].setOnAction((e) -> {
        // edit
            if (editStage != null) {
                return;
            }
            if (table.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Es ist kein Eintrag in der Tabelle ausgewählt.");
                alert.showAndWait();
                return;
            }
            showEditDialogue(table.getSelectionModel().getSelectedItem());
            //table.refresh();
            editStage = null;
        });
        buttons[2].setOnAction((e) -> {
        // delete
            if (table.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Es ist kein Eintrag in der Tabelle ausgewählt.");
                alert.showAndWait();
                return;
            }
            dataList.remove(table.getSelectionModel().getSelectedItem());
        });
        buttons[3].setOnAction((e) -> {
        // edit all
            if (editAllStage != null) {
                return;
            }
            showEditAllDialogue();
            //table.refresh();
            editAllStage = null;
        });

    }

    private void showAddDialogue() {

        Label gLabel = new Label("Entgeltgruppe: ");
        Label sLabel = new Label("Gehalt: ");

        TextField gInput = new TextField();
        TextField sInput = new TextField();

        Button addOKButton = new Button("Hinzufügen");
        Button addCancelButton = new Button("Abbrechen");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(gLabel, gInput);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(sLabel, sInput);
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(addOKButton, addCancelButton);

        VBox addVBox = new VBox();
        addVBox.getChildren().addAll(hbox1, hbox2, hbox3);

        Pane addPane = new Pane();
        addPane.getChildren().add(addVBox);
        addStage = new Stage();
        addStage.setScene(new Scene(addPane));

        addOKButton.setOnAction((e) -> {
            if (checkAndAddData(gInput.textProperty().get(), sInput.textProperty().get())) {
                addStage.close();
            }
        });

        addCancelButton.setOnAction((e) -> {
            addStage.close();
        });

        addStage.showAndWait();
    }

    private void showEditDialogue(StringDoublePair data) {

        Label gLabel = new Label("Entgeltgruppe: ");
        Label sLabel = new Label("Gehalt: ");

        TextField gInput = new TextField(data.getString());
        TextField sInput = new TextField(String.valueOf(data.getDouble()));

        Button addOKButton = new Button("Ändern");
        Button addCancelButton = new Button("Abbrechen");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(gLabel, gInput);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(sLabel, sInput);
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(addOKButton, addCancelButton);

        VBox addVBox = new VBox();
        addVBox.getChildren().addAll(hbox1, hbox2, hbox3);

        Pane addPane = new Pane();
        addPane.getChildren().add(addVBox);
        editStage = new Stage();
        editStage.setScene(new Scene(addPane));

        addOKButton.setOnAction((e) -> {
            if (checkAndEditData(data, gInput.textProperty().get(), sInput.textProperty().get())) {
                editStage.close();
            }
        });

        addCancelButton.setOnAction((e) -> {
            editStage.close();
        });
        editStage.showAndWait();
    }

    private void showEditAllDialogue() {

        Label pLabel = new Label("Entgeltgruppe: ");

        TextField pInput = new TextField();

        Button addOKButton = new Button("Anpassen");
        Button addCancelButton = new Button("Abbrechen");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(pLabel, pInput);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addOKButton, addCancelButton);

        VBox addVBox = new VBox();
        addVBox.getChildren().addAll(hbox1, hbox2);

        Pane addPane = new Pane();
        addPane.getChildren().add(addVBox);
        editAllStage = new Stage();
        editAllStage.setScene(new Scene(addPane));

        addOKButton.setOnAction((e) -> {
            try {
                double val = Double.parseDouble(pInput.textProperty().get());
                for (StringDoublePair d : dataList) {
                    d.setDouble(d.getDouble() * (100 + val) / 100);
                }
                editAllStage.close();
            } catch (NumberFormatException | NullPointerException ex) {
            }
        });

        addCancelButton.setOnAction((e) -> {
            editAllStage.close();
        });
        editAllStage.showAndWait();
    }

    private boolean checkAndEditData(StringDoublePair data, String gInput, String sInput) {
        if (!checkData(gInput, sInput)) {
            return false;
        }
        double result = Double.parseDouble(sInput);
        data.setString(gInput);
        data.setDouble(result);
        return true;
    }

    private boolean checkAndAddData(String gInput, String sInput) {
        if (!checkData(gInput, sInput)) {
            return false;
        }
        double result = Double.parseDouble(sInput);
        dataList.add(new StringDoublePair(gInput, result));
        return true;
    }

    private boolean checkData(String gInput, String sInput) {
        if (gInput == null || sInput  == null || gInput.isBlank() || sInput.isBlank()) {
            return false;
        }
        double result;
        try {
            Double.parseDouble(sInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Pane getPane() {
        return root;
    }
}
