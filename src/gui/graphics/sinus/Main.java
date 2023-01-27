package gui.graphics.sinus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int NUM_POINTS = 1000;

    public void start(Stage primaryStage) {
        SinusPresenter presenter = new SinusPresenter();
        SinusView view = new SinusView(presenter);
        SinusModel model = new SinusModel();
        presenter.setView(view);
        presenter.setModel(model);
        primaryStage.setTitle("Sinus");
        Scene scene = new Scene(view.getUI(), 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
