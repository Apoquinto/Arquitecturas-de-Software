import view.*;
import model.Model;
import controller.Controller;

public class Main {
    public static void main(String[] args) {
        CLIConfig viewConfig = new CLIConfig();
        ICLI view = new CLIView(viewConfig);
        Model model = new Model();
        Controller controller = new Controller(view, model);

        controller.start();
    }
}
