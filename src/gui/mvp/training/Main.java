package gui.mvp.training;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        Presenter p = new Presenter();
        View v = new View(p);
        Model m = new Model();
        p.setupModelView(m, v);

        Scene scene = new Scene(v.getUI(), 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(200);
        primaryStage.setTitle("Trainingseinheiten");
        primaryStage.show();
    }
}
