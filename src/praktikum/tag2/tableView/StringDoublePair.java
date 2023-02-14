package praktikum.tag2.tableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class StringDoublePair {
    private final SimpleStringProperty stringProperty = new SimpleStringProperty();
    private final SimpleDoubleProperty doubleProperty = new SimpleDoubleProperty();

    public StringDoublePair(String name, double val) {
        stringProperty.set(name);
        doubleProperty.set(val);
    }

    public String getString() {
        return stringProperty.get();
    }

    public void setString(String s) {
        stringProperty.set(s);
    }

    public SimpleStringProperty stringProperty() {
        return stringProperty;
    }

    public double getDouble() {
        return doubleProperty.get();
    }

    public void setDouble(double val) {
        doubleProperty.set(val);
    }

    public SimpleDoubleProperty doubleProperty() {
        return doubleProperty;
    }
}
