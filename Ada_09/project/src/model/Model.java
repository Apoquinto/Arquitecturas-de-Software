package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private final FileHandler fileHandler;
    private Poll poll;

    public Model() {
        this.logger = Log.getLogger();
        this.poll = new Poll("A simple Pool");
        fileHandler = new FileHandler();
    }

    public void registerVote(String target){
        this.poll.registerVote(target);
    }

    public void registerOption(String newOption){
        this.poll.registerOption(newOption);
    }

    public void getLabels(){
        this.poll.getOptionsNames();
    }

    public Collection<PollOption> getOptionsData(){ return this.poll.getOptions(); }

    public void generateHistoryFile(){
        ArrayList<String> history = new ArrayList<>();
        this.poll.getEventsHistory().forEach((event) -> history.add(event.toString()));
        fileHandler.writeFile("VotesRecord.txt", history);
    }

    public void readInfo() {
        logger.info("");
        /*
        retrieveProductNames();
        retrieveVotes(null, firstProductVotes);
        retrieveVotes(null, secondProductVotes);
        retrieveVotes(null, thirdProductVotes);
        */
        // update observer
    }

    private void retrieveProductNames() {
        logger.info("");
        // File Manager task
        // set names
    }

    private void retrieveVotes(String filename, ArrayList<String> targetArray) {
        logger.info("");
        // File Manager task
        // Add votes to array
    }

    public void saveVotesInFile() {
        logger.info("");
        // Save the three arrays in the three different files
        // specified in the UML (split it in private as retrieve votes)
    }

    // Vote for ...
    // At the end of the voting phase, you always have to update the observer!
}
