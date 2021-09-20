import presentation.*;

public class Main {
    public static void main(String[] args) {
        PresentationLayer presentationLayer = new PresentationModule(new ColorConfiguration());
        presentationLayer.run();
    }
}
