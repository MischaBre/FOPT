package gui.country.combo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CountryInfo extends Application
{
    private final ObservableList<Country> countryList = FXCollections.observableArrayList();;
    private final ComboBox<Country> cobo1 = new ComboBox<>();
    private final CheckBox chbo1 = new CheckBox("exakte Angaben");
    private final List<Label> labels = new ArrayList<>();

    public CountryInfo()
    {
        cobo1.setId("countrySelector");
        chbo1.setId("exactValues");
        countryList.add(new Country("Kanada", "Ottawa", 34278406, 9984670));
        countryList.add(new Country("Luxemburg", "Luxemburg", 511480, 2586));
    }

    public void start(Stage primaryStage)
    {
        Scene scene = initUI();

        cobo1.setOnAction(e -> countryUpdate(cobo1.getValue()));
        chbo1.setOnAction(e -> countryUpdate(cobo1.getValue()));

        countryUpdate(cobo1.getValue());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.show();
    }

    private void countryUpdate(Country c)
    {
        labels.get(0).textProperty().set(cobo1.getValue().getName());
        labels.get(1).textProperty().set(cobo1.getValue().getCapital());
        labels.get(2).textProperty().set(convertLongToStringRounded(cobo1.getValue().getPeople()));
        labels.get(3).textProperty().set(convertLongToStringRounded(cobo1.getValue().getArea()));
        labels.get(4).textProperty().set(String.format("%d", cobo1.getValue().getBevDichte()));
    }

    private Scene initUI()
    {
        cobo1.setItems(countryList);
        cobo1.setMinWidth(100);
        cobo1.setValue(countryList.get(0));

        Label l00 = new Label("Land:");
        Label l10 = new Label("Hauptstadt:");
        Label l20 = new Label("Einwohner");
        Label l30 = new Label("Flaeche (in qkm):");
        Label l40 = new Label("Bevoelkerungsdichte (in Personen pro qkm):");

        /*
        labels.add(l00);
        labels.add(l10);
        labels.add(l20);
        labels.add(l30);
        labels.add(l40);
        */

        VBox vb2 = new VBox();
        vb2.getChildren().add(l00);
        vb2.getChildren().add(l10);
        vb2.getChildren().add(l20);
        vb2.getChildren().add(l30);
        vb2.getChildren().add(l40);

        HBox hb1 = new HBox();
        hb1.getChildren().add(vb2);

        Label l01 = new Label();
        l01.setId("countryName");
        Label l11 = new Label();
        l11.setId("capital");
        Label l21 = new Label();
        l21.setId("population");
        Label l31 = new Label();
        l31.setId("area");
        Label l41 = new Label();
        l41.setId("density");

        labels.add(l01);
        labels.add(l11);
        labels.add(l21);
        labels.add(l31);
        labels.add(l41);

        VBox vb3 = new VBox();
        vb3.getChildren().add(l01);
        vb3.getChildren().add(l11);
        vb3.getChildren().add(l21);
        vb3.getChildren().add(l31);
        vb3.getChildren().add(l41);

        hb1.getChildren().add(vb3);

        VBox vb1 = new VBox();
        vb1.getChildren().add(cobo1);
        vb1.getChildren().add(chbo1);
        vb1.getChildren().add(hb1);

        VBox root = new VBox();
        root.getChildren().add(vb1);

        Scene scene = new Scene(root, 500,300);
        return scene;
    }

    private String convertLongToStringRounded(Long num)
    {
        if (chbo1.isSelected())
        {
            return NumberFormat.getNumberInstance(Locale.GERMAN).format(num);
        }
        else
        {
            return num >= 1_000_000 ? num / 1_000_000 + " Mill." : NumberFormat.getNumberInstance(Locale.GERMAN).format(num / 1000) + ".000";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
