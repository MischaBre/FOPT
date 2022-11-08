package gui.mvp.training;

public class ModelTester {

    public static Model m = new Model();

    public static void main(String[] args) {
        try {
            m.addTraining(new TrainingUnit("a", 1, 2));
            m.addTraining(new TrainingUnit("a", 2, 3));
            m.addTraining(new TrainingUnit("b", 1, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("Doppelter Eintrag");
        } finally {
            System.out.println(m);
        }
        m.addTraining(new TrainingUnit("b", 1, 2));
        m.addTraining(new TrainingUnit("c", 2, 3));

        System.out.println(m);

        for (String s : m.getAllMarkers()) {
            System.out.println(s);
        }
    }
}
