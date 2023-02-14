package praktikum.tag2.grafik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseMotionListener;

public class Main extends Application {

    Line line;
    Circle circle;
    Rectangle rectangle;

    @Override
    public void start(Stage stage) throws Exception {
        ToggleGroup choice = new ToggleGroup();

        RadioButton lineButton = new RadioButton("Linie");
        lineButton.setToggleGroup(choice);
        lineButton.setSelected(true);

        RadioButton circleButton = new RadioButton("Kreis");
        circleButton.setToggleGroup(choice);

        RadioButton squareButton = new RadioButton("Rechteck");
        squareButton.setToggleGroup(choice);

        HBox hbox = new HBox(lineButton, circleButton, squareButton);
        Pane pane = new Pane();
        pane.setMinHeight(250);



        pane.setOnMousePressed((e) -> {
            if (choice.getSelectedToggle() == lineButton) {
                line = new Line(e.getX(),e.getY(),e.getX(),e.getY());
                line.setStrokeWidth(0.5);
                pane.getChildren().add(line);
            } else if (choice.getSelectedToggle() == circleButton) {
                circle = new Circle(e.getX(), e.getY(), 0);
                // noch Füllung
                pane.getChildren().add(circle);
            } else {
                rectangle = new Rectangle(e.getX(), e.getY());
                pane.getChildren().add(rectangle);
            }
        });
        pane.setOnMouseDragged((e) -> {
            if (choice.getSelectedToggle() == lineButton) {
                line.setEndX(e.getX());
                line.setEndY(e.getY());
            } else if (choice.getSelectedToggle() == circleButton) {
                circle.setRadius(
                        Math.max(
                                Math.abs(e.getX()-circle.getCenterX()),
                                Math.abs(e.getY()-circle.getCenterY())));
            } else {
                //
            }
        });
        pane.setOnMouseReleased((e) -> {
            if (choice.getSelectedToggle() == lineButton) {
                line.setStrokeWidth(2);
                line = null;
            } else if (choice.getSelectedToggle() == circleButton) {
                circle.setStrokeWidth(2);
                circle = null;
            } else {
            }

        });
        VBox vbox = new VBox(hbox, pane);
        stage.setScene(new Scene(vbox, 600, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
