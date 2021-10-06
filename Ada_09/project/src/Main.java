import controller.Controller;
import model.Model;
import model.Log;
import view.*;

public class Main {
    public static void main(String[] args) {
        /*
        // IMPORTANT! Place this in the beginning of every method, it is part of the
        // homework's log requirement.
        Log.getLogger().info("");
        
        // Execution
        View view = new View();
        BarChart barChart = new BarChart();
        CakeChart cakeChart = new CakeChart();
        // Note: Create the observer, subscribe the views and give it
        // to the model via constructor
        Model model = new Model();
        Controller controller = new Controller(model, view, barChart, cakeChart);

        controller.start();
        */
        Model model = new Model();

        model.registerOption("A");
        model.registerOption("B");
        model.registerOption("C");

        model.registerVote("B");
        model.registerVote("A");
        model.registerVote("A");
        model.registerVote("B");
        model.registerVote("C");
        model.registerVote("A");
        model.registerVote("C");

        model.generateHistoryFile();
    }
}