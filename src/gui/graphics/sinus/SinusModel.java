package gui.graphics.sinus;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;

public class SinusModel {

    public SinusModel() {
    }

    private double getSinusOf(double x, double amplitude, double freq, double phase) {
        double result = amplitude * Math.sin(freq*x + phase);
        //System.out.println(result);
        return result;

    }

    public Double[] getSinusCurve(Double[] sineAttr, int resolution) {
        Double[] result = new Double[resolution * 2];
        double amplitude = sineAttr[0];
        double freq = sineAttr[1];
        double phase = sineAttr[2];
        double extend = sineAttr[3];
        double step = 2 * extend / resolution;
        double x = -extend;
        for (int i = 0; i < 2 * resolution; i += 2) {
            result[i] = x;
            result[i + 1] = getSinusOf(x, amplitude, freq, phase);
            x += step;
        }
        return result;
    }
}
