package model;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Event {
    private Logger logger;
    private String eventName;
    private LocalDateTime datetime;

    public Event(String eventName){
        this(eventName, "now");
    }

    public Event(String eventName, String timeText) {
        logger = Log.getLogger();
        this.eventName = eventName;
        this.datetime = generateByGivenTime(timeText);
    }

    private LocalDateTime generateByGivenTime(String timeText) {
        // Not recorded because it is part of the constructor
        LocalDateTime time;

        try {
            // timeText = yyyy-mo-ddThh:mi:ss.nnnnnnnnn
            String[] dateAndTime = timeText.split("T");

            if (dateAndTime.length == 2) {
                String[] dateSegments = dateAndTime[0].split("-");
                String[] timeSegments = dateAndTime[1].split(":");
                String[] secondsAndNano = timeSegments[2].split("\\.");

                if (dateSegments.length == 3 &&
                        timeSegments.length == 3 &&
                        secondsAndNano.length == 2) {
                    time = LocalDateTime.of(
                            Integer.parseInt(dateSegments[0]),      // Year
                            Integer.parseInt(dateSegments[1]),      // Month
                            Integer.parseInt(dateSegments[2]),      // Day
                            Integer.parseInt(timeSegments[0]),      // Hour
                            Integer.parseInt(timeSegments[1]),      // Minute
                            Integer.parseInt(secondsAndNano[0]),    // Second
                            Integer.parseInt(secondsAndNano[1])     // Nanosecond
                    );
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            time = LocalDateTime.now();
        }

        return time;
    }

    public String getEventName(){
        logger.info("");
        return this.eventName;
    }

    @Override
    public String toString() {
        logger.info("");
        return datetime.toString();
    }
}
