package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Poll {
    private HashMap<String, PollOption> options;
    private ArrayList<Event> eventsHistory;
    private String title;

    public Poll(String title) {
        this.eventsHistory = new ArrayList<>();
        this.options = new HashMap<>();
        this.title = title;
    }

    public void registerOption(String name) {
        PollOption newOption = new PollOption(name);
        this.options.put(name, newOption);
        this.eventsHistory.add(new Event("Add new option", "Nueva opción " + name + " agregada"));
    }

    public void registerVote(String option){
        this.options.get(option).addVote(1);
        this.eventsHistory.add(new Event("Add vote", "Nuevo voto agreago a " + option));
    }

    public Collection<PollOption> getOptions(){
        return options.values();
    }

    public Collection<String> getOptionsNames(){
        return options.keySet();
    }

    public ArrayList<Event> getEventsHistory(){
        return this.eventsHistory;
    }
}
