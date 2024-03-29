package tests.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DrawCircles extends Application {

    private static final double RADIUS = 20;
    private Pane graphicsPane;
    private Circle c;

    @Override
    public void start(Stage primaryStage) throws Exception {
        graphicsPane = new Pane();
        graphicsPane.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        graphicsPane.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        graphicsPane.setOnMouseReleased(e -> mouseReleased());
        primaryStage.setTitle("Rote Kreise");
        primaryStage.setScene(new Scene(graphicsPane, 350, 120));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void mousePressed(double x, double y) {
        c = new Circle();
        c.setStroke(Color.RED);
        c.setFill(Color.TRANSPARENT);
        c.setCenterX(x);
        c.setCenterY(y);
        c.setRadius(RADIUS);
        graphicsPane.getChildren().add(c);
    }

    private void mouseDragged(double x, double y) {
        c.setCenterX(x);
        c.setCenterY(y);
    }

    private void mouseReleased() {
        c.setFill(Color.RED);
    }

}
