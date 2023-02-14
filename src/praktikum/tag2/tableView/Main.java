package praktikum.tag2.tableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TableViewView mainView = new TableViewView();
        Pane root = mainView.getPane();
        stage.setScene(new Scene(root, 500,500));
        stage.setTitle("TableView");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
