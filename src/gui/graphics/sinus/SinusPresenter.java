package gui.graphics.sinus;

public class SinusPresenter {
    private SinusModel model;
    private SinusView view;

    public SinusPresenter() {
    }

    public void newInputAction(Double[] sineAttr) {
        Double[] sineWavePoints = model.getSinusCurve(sineAttr, Main.NUM_POINTS);
        view.redraw(sineWavePoints);
    }

    public void setModel(SinusModel model) {
        this.model = model;
    }

    public void setView(SinusView view) {
        this.view = view;
    }
}
