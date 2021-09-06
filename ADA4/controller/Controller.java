package controller;

import java.util.Arrays;

import model.Model;
import view.ICLI;

public class Controller {
    private Model model;
    private ICLI view;

    public Controller(ICLI view, Model model){
        this.view = view;
        this.model = model;
    }

    public void start(){
        NamesParser parser = new ConcreteParser();

        view.print(model.getTeamString());

        String[] namesArray = parser.parse(model.readFile());
        Arrays.sort(namesArray);

        view.update(namesArray);
    }
}
