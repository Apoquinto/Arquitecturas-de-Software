package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Poll {
    private Logger logger;
    private HashMap<String, PollOption> options;
    private ArrayList<Event> eventsHistory;
    private String title;

    public Poll(String title) {
        this.logger = Log.getLogger();
        this.eventsHistory = new ArrayList<>();
        this.options = new HashMap<>();
        this.title = title;
    }

    public void registerOption(String name) {
        logger.info("");
        if (options.size() < 3) {
            PollOption newOption = new PollOption(name);
            this.options.put(name, newOption);
            this.eventsHistory.add(new Event(name, "Nueva opción " + name + " agregada"));
        }
    }

    public void registerVote(String option){
        logger.info("");
        this.options.get(option).addVote(1);
        this.eventsHistory.add(new Event(option, "Nuevo voto agreago a " + option));
    }

    public Collection<PollOption> getOptions(){
        logger.info("");
        return options.values();
    }

    public void setEvents(ArrayList<Event> events){
        this.eventsHistory = events;
    }

    public Collection<String> getOptionsNames(){
        logger.info("");
        return options.keySet();
    }

    public ArrayList<Event> getEventsHistory(){
        logger.info("");
        return this.eventsHistory;
    }

    public ArrayList<String> getEventsHistoryByName(String name){
        logger.info("");
        ArrayList<String> events = new ArrayList<>();
        events.add(options.get(name).toString());
        for (Event event: eventsHistory) {
            if (event.getEventName().equals(name)){
                events.add(event.toString());
            }
        }
        return events;
    }
}
