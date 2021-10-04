package model;

import java.time.LocalDateTime;

public class Vote {
    private LocalDateTime voteDate;

    public Vote() {
        this.voteDate = LocalDateTime.now();
    }

    public Vote(int year, int month, int dayOfMonth, int hour,
                int minute, int second, int nanoOfSeconds) {
        this.voteDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSeconds);
    }

    @Override
    public String toString() {
        Log.getLogger().info("");
        return voteDate.toString();
    }
}
