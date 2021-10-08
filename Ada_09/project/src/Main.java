import controller.Controller;
import model.Model;
import model.Log;
import view.*;

public class Main {
    public static void main(String[] args) {
        // IMPORTANT! Place this in the beginning of every method, it is part of the
        // homework's log requirement.
        // PD. We are not saving the activity of any constructor.
        Log.getLogger().info("");
        
        // Execution
        View view = new View();
        BarChart barChart = new BarChart();
        CakeChart cakeChart = new CakeChart();
        Model model = new Model();
        Controller controller = new Controller(model, view, barChart, cakeChart);

        controller.start();
    }
}