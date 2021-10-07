package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private final FileHandler fileHandler;
    private Poll poll;

    public Model() {
        this.logger = Log.getLogger();
        this.poll = new Poll("A simple Pool");
        this.fileHandler = new FileHandler();
    }

    public void registerVote(String target){
        logger.info("");
        this.poll.registerVote(target);
    }

    public void registerOption(String newOption){
        logger.info("");
        this.poll.registerOption(newOption);
    }

    public Collection<PollOption> getOptionsData(){
        logger.info("");
        return this.poll.getOptions();
    }

    public void generateVotesFiles(){
        logger.info("");
        for (String name: poll.getOptionsNames()) {
            fileHandler.writeFile(name + ".txt", poll.getEventsHistoryByName(name));
        }
    }

    public Iterator<PollOption> getOptionsIterator(){
        logger.info("");
        return poll.getOptions().iterator();
    }

    public void readInfo(String fileName) {
        logger.info("");
        for (String option : fileHandler.readFile(fileName)) {
            registerOption(option);
        }
    }
}
