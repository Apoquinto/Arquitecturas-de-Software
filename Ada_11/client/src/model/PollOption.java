package model;

import java.util.logging.Logger;

public class PollOption {
    private Logger logger;
    private String name;
    private int count;

    public PollOption(String name) {
        this.logger =  Log.getLogger();
        this.name = name;
        this.count = 0;
    }

    public String getName() {
        logger.info("");
        return name;
    }

    public void setName(String name) {
        logger.info("");
        this.name = name;
    }

    public void setCount(int count) {
        logger.info("");
        this.count = Math.max(count, 0);
    }

    public int getCount(){
        logger.info("");
        return this.count;
    }

    public void addVote(){
        logger.info("");
        this.count++;
    }

    @Override
    public String toString() {
        logger.info("");
        return "Have been registered " + count + " votes for " + name;
    }
}
