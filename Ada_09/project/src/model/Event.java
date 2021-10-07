package model;

import java.time.LocalDateTime;

public class Event {
    private String eventName;
    private String message;
    private LocalDateTime datetime;

    public Event(String eventName, String message){
        this.eventName = eventName;
        this.message = message;
        this.datetime = LocalDateTime.now();
    }

    public String getEventName(){
        return this.eventName;
    }

    @Override
    public String toString() {
        return "[ " + eventName + " ] " + message + " a las " + datetime.toString();
    }
}
