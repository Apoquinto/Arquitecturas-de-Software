package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Poll {
    private Logger logger;
    private HashMap<String, PollOption> options;
    private ArrayList<Event> eventsHistory;

    public Poll() {
        this.logger = Log.getLogger();
        this.eventsHistory = new ArrayList<>();
        this.options = new HashMap<>();
    }

    public boolean registerOption(String name) {
        logger.info("");
        boolean canBeRegistered = options.size() < 3;

        if (canBeRegistered) {
            PollOption newOption = new PollOption(name);
            options.put(name, newOption);
        }

        return canBeRegistered;
    }

    public void registerVote(String option){
        logger.info("");
        options.get(option).addVote();
        eventsHistory.add(new Event(option));
    }

    public void registerVote(String option, String timeText){
        logger.info("");
        options.get(option).addVote();
        eventsHistory.add(new Event(option, timeText));
    }

    public Collection<PollOption> getOptions(){
        logger.info("");
        return options.values();
    }

    public void setEvents(ArrayList<Event> events){
        logger.info("");
        this.eventsHistory = events;
    }

    public Collection<String> getOptionsNames(){
        logger.info("");
        return options.keySet();
    }

    public ArrayList<Event> getEventsHistory(){
        logger.info("");
        return eventsHistory;
    }

    public ArrayList<String> getEventsHistoryByName(String name){
        logger.info("");
        ArrayList<String> events = new ArrayList<>();

        for (Event event: eventsHistory) {
            if (event.getEventName().equals(name)){
                events.add(event.toString());
            }
        }

        return events;
    }
}
