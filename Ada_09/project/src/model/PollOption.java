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

    public void addVote(int noVotes){
        logger.info("");
        this.count = Math.max(this.count + noVotes, 0);
    }

    @Override
    public String toString() {
        logger.info("");
        return "Se han registrado " + count + " votos para la opción " + name;
    }
}
