package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private final FileHandler fileHandler;
    private Client client;
    private Poll poll;
    private ArrayList<String> vars;

    public Model() {
        this.logger = Log.getLogger();
        this.poll = new Poll();
        this.fileHandler = new FileHandler();
        this.client = new Client();
        this.client.startConnection("localhost", 8020);
        this.vars = new ArrayList<>();
    }

    public void registerVote(String target){
        logger.info("");
        vars.clear();
        vars.add("servicio");
        vars.add("votar");
        vars.add(target);
        vars.add("1");
        this.client.sendMessage(new CallsFormatter().generateRequest("ejecutar", 2, vars.iterator()));
        this.poll.registerVote(target);
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

    public void closeClient(){
        this.client.stopConnection();
    }

    public Iterator<PollOption> getOptionsIterator(){
        logger.info("");
        return poll.getOptions().iterator();
    }

    public void readInfo(String fileName) {
        logger.info("");

        for (String option: fileHandler.readFile(fileName)) {
            if (!poll.registerOption(option)) break;

            for (String voteTime : fileHandler.readFile(option + ".txt")) {
                if (!voteTime.equals("")) poll.registerVote(option, voteTime);
            }
        }
    }
}
