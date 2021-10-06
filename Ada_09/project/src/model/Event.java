package model;

import java.time.LocalDateTime;

public class Event {
    private String event;
    private String message;
    private LocalDateTime datetime;

    public Event(String event, String message){
        this.event = event;
        this.message = message;
        this.datetime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[ " + event + " ] " + message + " a las " + datetime.toString();
    }
}
