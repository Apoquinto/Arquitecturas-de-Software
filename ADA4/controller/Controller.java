package controller;

import java.util.Arrays;

import model.Model;
import view.CLIConfig;
import view.CLIView;

public class Controller {
    private Model model;
    private CLIView view;

    public Controller(){
        model = new Model();
        view = new CLIView(new CLIConfig());
    }

    public void start(){
        view.print(model.teamString());
        String[] namesArray = model.readFile();
        NamesParser parser = new ConcreteParser();
        Arrays.sort(parser.parse(namesArray));
        view.update(namesArray);
    }
}
