package clock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        ClockPresenter p = new ClockPresenter();
        ClockView v = new ClockView(p);
        Clock c = new Clock();
        p.setModelAndView(c, v);
        p.reset();

        Scene scene = new Scene(v.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stoppuhr");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
