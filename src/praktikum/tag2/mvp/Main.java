package praktikum.tag2.mvp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        View view = new View();
        Model model = new Model();
        stage.setScene(new Scene(view.getRoot(), 500,500));
        stage.setTitle("MVP");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
