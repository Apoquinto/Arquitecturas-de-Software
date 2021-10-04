package model;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Vote {
    private final Logger logger;
    private final LocalDateTime voteDate;

    public Vote() {
        this.logger =  Log.getLogger();
        this.voteDate = LocalDateTime.now();
    }

    public Vote(int year, int month, int dayOfMonth, int hour,
                int minute, int second, int nanoOfSeconds) {
        this.logger =  Log.getLogger();
        this.voteDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSeconds);
    }

    @Override
    public String toString() {
        logger.info("");
        return voteDate.toString();
    }
}
