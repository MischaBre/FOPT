package clock;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockView {
    private VBox root;
    private Label timeLabel = new Label();
    private Button startB = new Button("Start");
    private Button stopB = new Button("Stop");
    private Button resetB = new Button("Reset");
    private ClockPresenter presenter;

    public ClockView(ClockPresenter presenter) {
        this.presenter = presenter;

        initUI();
        setupListener();
    }

    private void initUI() {
        root = new VBox(timeLabel, startB, stopB, resetB);
        root.setPadding(new Insets(10));
    }

    private void setupListener() {
        startB.setOnAction(e -> presenter.start());
        stopB.setOnAction(e -> presenter.stop());
        resetB.setOnAction(e -> presenter.reset());
    }

    public Pane getRoot() {
        return root;
    }

    public void updateTime(long elapsedTime) {
        Date date = new Date(elapsedTime);
        DateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        timeLabel.setText(formatter.format(date));
    }
}
