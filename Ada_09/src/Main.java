import controller.Controller;
import model.Model;
import model.Log;
import view.View;

public class Main {
    public static void main(String[] args) {
        // Place this in the beggining of every method, it is part of the
        // homeworks's log requirement.
        Log.getLogger().info("");
        
        // Execution
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.start();
    }
}