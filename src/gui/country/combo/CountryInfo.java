package gui.country.combo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class CountryInfo extends Application
{
    private final ObservableList<Country> countryList = FXCollections.observableArrayList();
    private final ComboBox<Country> cobo1 = new ComboBox<>();
    private final CheckBox chbo1 = new CheckBox("exakte Angaben");
    private final List<Label> labels = new ArrayList<>();
    private final Button b1 = new Button("Hinzufügen");
    private final Button b2 = new Button("Löschen");
    private final List<TextField> textFields = new ArrayList<>();

    public CountryInfo()
    {
        cobo1.setId("countrySelector");
        cobo1.setPromptText("Keine Länder vorhanden");
        chbo1.setId("exactValues");
        countryList.add(new Country("Kanada", "Ottawa", 34278406, 9984670));
        countryList.add(new Country("Luxemburg", "Luxemburg", 511480, 2586));
    }

    public void start(Stage primaryStage)
    {
        Scene scene = initUI();

        cobo1.setOnAction(e -> countryUpdate(cobo1.getValue()));
        chbo1.setOnAction(e -> countryUpdate(cobo1.getValue()));
        b1.setOnAction(e -> clickedAdd());
        b2.setOnAction(e -> clickedDelete());

        if (!countryList.isEmpty())
        {
            cobo1.setValue(countryList.get(0));
            countryUpdate(cobo1.getValue());
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.show();
    }

    private void countryUpdate(Country c)
    {
        if (c == null)
        {
            for (Label l : labels)
            {
                l.textProperty().set("");
            }
        }
        else
        {
            labels.get(0).textProperty().set(c.getName());
            labels.get(1).textProperty().set(c.getCapital());
            labels.get(2).textProperty().set(convertLongToStringRounded(c.getPeople()));
            labels.get(3).textProperty().set(convertLongToStringRounded(c.getArea()));
            labels.get(4).textProperty().set(c.getBevDichte());
        }
    }

    private boolean areTextFieldsValid()
    {
        try
        {
            long l1 = Long.parseLong(textFields.get(2).textProperty().get());
            long l2 = Long.parseLong(textFields.get(3).textProperty().get());
            return l1 >= 0 && l2 >= 0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    private boolean areTextFieldsEmpty()
    {
        for (TextField t : textFields)
        {
            if (Objects.equals(t.textProperty().get(), ""))
            {
                return true;
            }
        }
        return false;
    }

    private void emptyTextFields()
    {
        for (TextField t : textFields)
        {
            t.textProperty().set("");
        }
    }

    private void clickedAdd()
    {
        if (!areTextFieldsEmpty() && areTextFieldsValid())
        {
            countryList.add(new Country(textFields.get(0).textProperty().get(),
                    textFields.get(1).textProperty().get(),
                    Long.parseLong(textFields.get(2).textProperty().get()),
                    Long.parseLong(textFields.get(3).textProperty().get())));
            emptyTextFields();
        }
        if (countryList.size() == 1)
        {
            cobo1.getSelectionModel().selectFirst();
        }
    }

    private void clickedDelete()
    {
        if (countryList.isEmpty())
        {
            return;
        }
        Country toDelete = cobo1.getValue();
        int index = countryList.indexOf(toDelete);
        if (countryList.size() == 1)
        {
            cobo1.getSelectionModel().clearSelection();
            countryList.remove(index);
            cobo1.setPromptText("Keine Länder vorhanden");
            countryUpdate(null);
            return;
        }
        else if (index == countryList.size() - 1)
        {
            index--;
        }
        cobo1.getSelectionModel().select(index + 1);
        countryList.remove(toDelete);
    }

    private Scene initUI()
    {
        cobo1.setItems(countryList);
        cobo1.setMinWidth(100);

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

        TextField tf1 = new TextField();
        tf1.promptTextProperty().set("Land");
        tf1.setId("countryField");
        TextField tf2 = new TextField();
        tf2.promptTextProperty().set("Hauptstadt");
        tf2.setId("capitalField");
        TextField tf3 = new TextField();
        tf3.promptTextProperty().set("Einwohner");
        tf3.setId("populationField");
        TextField tf4 = new TextField();
        tf4.promptTextProperty().set("Fläche");
        tf4.setId("areaField");

        b1.setId("add");
        b2.setId("delete");

        textFields.add(tf1);
        textFields.add(tf2);
        textFields.add(tf3);
        textFields.add(tf4);

        HBox hb2 = new HBox();
        hb2.getChildren().add(tf1);
        hb2.getChildren().add(tf2);
        hb2.getChildren().add(tf3);
        hb2.getChildren().add(tf4);
        hb2.getChildren().add(b1);

        VBox vb1 = new VBox();
        vb1.getChildren().add(cobo1);
        vb1.getChildren().add(chbo1);
        vb1.getChildren().add(hb1);
        vb1.getChildren().add(hb2);
        vb1.getChildren().add(b2);

        VBox root = new VBox();
        root.getChildren().add(vb1);

        return new Scene(root, 800,300);
    }

    private String convertLongToStringRounded(Long num)
    {
        if (chbo1.isSelected())
        {
            return NumberFormat.getNumberInstance(Locale.GERMAN).format(num);
        }
        else
        {
            if (num < 1000)
            {
                return String.valueOf(num);
            }
            if (num < 1000000)
            {
                return Math.round(num / 1000.0) + ".000";
            }
            return Math.round(num / 1000000.0) + " Mill.";
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
