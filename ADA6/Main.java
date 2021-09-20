import presentation.PresentationModule;
import presentation.ColorConfiguration;

public class Main {
    public static void main(String[] args) {
        PresentationModule presentationLayer = new PresentationModule(new ColorConfiguration());
        presentationLayer.run();
    }
}
