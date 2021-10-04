package controller;

import model.Model;
import model.Log;
import view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Listeners
    }

    public void start() {
        Log.getLogger().info("");
        // Do something...
    }
}
