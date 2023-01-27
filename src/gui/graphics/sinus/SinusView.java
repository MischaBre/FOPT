package gui.graphics.sinus;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class SinusView {
    private final SinusPresenter presenter;

    private final VBox root;
    private final Label funcLabel = new Label();
    private final Pane drawBox = new Pane();
    private final Slider ampSlider = new Slider();
    private final Slider freqSlider = new Slider();
    private final Slider phaseSlider = new Slider();
    private final Slider zoomSlider = new Slider();

    private final Slider[] sliders = new Slider[] {ampSlider, freqSlider, phaseSlider, zoomSlider};
    private final Polyline graph = new Polyline();
    private final ObservableList<Double> sinusPoints;

    public SinusView(SinusPresenter presenter) {
        this.presenter = presenter;
        this.root = new VBox(10);
        sinusPoints = graph.getPoints();
        initUI();
        setupListeners();
    }

    private void initUI() {
        drawBox.setMinHeight(300);
        drawBox.setMaxHeight(300);
        drawBox.getChildren().add(graph);

        //Clipping
        Rectangle clipRect = new Rectangle();
        clipRect.widthProperty().bind(drawBox.widthProperty());
        clipRect.heightProperty().bind(drawBox.heightProperty());
        drawBox.setClip(clipRect);
        drawBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        //add all to root
        root.getChildren().addAll(funcLabel, drawBox, ampSlider, freqSlider, phaseSlider, zoomSlider);
    }

    private void setupListeners() {
        for (Slider s : sliders) {
            s.setOnMouseReleased(e -> {
                Platform.runLater(() -> {
                    newInputAction();
                });
            });
        }
        root.widthProperty().addListener((o, oV, nV) -> {
            Platform.runLater(() -> {
                newInputAction();
            });
        });
        root.heightProperty().addListener((o, oV, nV) -> {
            Platform.runLater(() -> {
                newInputAction();
            });
        });
    }

    public Pane getUI() {
        return root;
    }

    private void newInputAction() {
        presenter.newInputAction(readSliders());
    }

    public void redraw(Double[] sineWavePoints) {
        Double[] sineWavePanePoints = reprojectCoords(sineWavePoints);
        sinusPoints.clear();
        sinusPoints.addAll(sineWavePanePoints);
    }

    private Double[] readSliders() {
        Double[] result = new Double[4];
        int i = 0;
        for (Slider s : sliders) {
            result[i++] = s.getValue();
        }
        return result;
    }

    private Double[] reprojectCoords(Double[] points) {
        double extend = zoomSlider.getValue();
        double shiftX = drawBox.getWidth() / 2;
        double shiftY = drawBox.getHeight() / 2;
        double scaleX = drawBox.getWidth() / (2 * extend);
        double scaleY = -drawBox.getHeight() / 6;
        for (int i = 0; i < points.length; i += 2) {
            points[i] = points[i] * scaleX + shiftX;
            points[i + 1] = points[i + 1] * scaleY + shiftY;
        }
        return points;
    }

}
