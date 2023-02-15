package praktikum.tag2.Uhr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    private Label clockLabel;
    private final SimpleStringProperty timeString = new SimpleStringProperty();

    @Override
    public void start(Stage stage) throws Exception {
        Thread timeThread = new Thread(() -> {
            LocalTime then = null;
            while (true) {
                try {

                    LocalTime now = LocalTime.now();
                    // oder ohne Abfrage und sleep(1000);
                    if (then == null || then.getSecond() != now.getSecond()) {
                        Platform.runLater(() -> {
                            timeString.set(
                                    now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            );
                        });
                        then = now;
                    }
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timeThread.setDaemon(true);
        timeThread.start();
        stage.setScene(new Scene(initUIAndGetPane(), 800, 250));
        stage.show();
    }

    private Pane initUIAndGetPane() {
        clockLabel = new Label();
        clockLabel.setFont(Font.font(200));
        clockLabel.textProperty().bind(timeString);
        Pane pane = new Pane();
        pane.getChildren().add(clockLabel);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
