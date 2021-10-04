package model;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private String firstProductName;
    private String secondProductName;
    private String thirdProductName;
    private final ArrayList<Vote> firstProductVotes;
    private final ArrayList<Vote> secondProductVotes;
    private final ArrayList<Vote> thirdProductVotes;

    public Model() {
        this.logger = Log.getLogger();
        this.firstProductName = "Producto 1";
        this.secondProductName = "Producto 2";
        this.thirdProductName = "Producto 3";
        this.firstProductVotes = new ArrayList<>();
        this.secondProductVotes = new ArrayList<>();
        this.thirdProductVotes = new ArrayList<>();
    }

    public String getFirstProductName() {
        logger.info("");
        return firstProductName;
    }

    public String getSecondProductName() {
        logger.info("");
        return secondProductName;
    }

    public String getThirdProductName() {
        logger.info("");
        return thirdProductName;
    }

    public int getP1VotesAmount() {
        logger.info("");
        return firstProductVotes.size();
    }

    public int getP2VotesAmount() {
        logger.info("");
        return secondProductVotes.size();
    }

    public int getP3VotesAmount() {
        logger.info("");
        return thirdProductVotes.size();
    }

    public void readInfo() {
        logger.info("");
        retrieveProductNames();
        retrieveVotes(null, firstProductVotes);
        retrieveVotes(null, secondProductVotes);
        retrieveVotes(null, thirdProductVotes);
        // update observer
    }

    private void retrieveProductNames() {
        logger.info("");
        // File Manager task
        // set names
    }

    private void retrieveVotes(String filename, ArrayList<Vote> targetArray) {
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
