package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private Client client;
    private Poll poll;
    private ArrayList<String> vars;

    public Model() {
        this.logger = Log.getLogger();
        this.poll = new Poll();
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
        CallsFormatter cf = new CallsFormatter();
        client.sendMessage(cf.generateRequest("ejecutar", 2, vars.iterator()));
        poll.registerVote(target);
        String[] time = poll.getEventsHistory().get(poll.getEventsHistory().size()-1).toString().split("T");
        vars.clear();
        vars.add("servicio");
        vars.add("registrar");
        vars.add("evento");
        vars.add("Se registró un voto para " + target);
        vars.add("fecha");
        vars.add(time[0] + " " + time[1]);
        client.sendMessage(cf.generateRequest("ejecutar", 3, vars.iterator()));
    }

    public Collection<PollOption> getOptionsData(){
        logger.info("");
        return this.poll.getOptions();
    }

    public void closeClient(){
        logger.info("");
        this.client.stopConnection();
    }

    public Iterator<PollOption> getOptionsIterator(){
        logger.info("");
        return poll.getOptions().iterator();
    }

    public void generatePoll() {
        logger.info("");
        vars.clear();
        vars.add("servicio");
        vars.add("contar");
        CallsFormatter cf = new CallsFormatter();
        String response = client.sendMessage(cf.generateRequest("ejecutar", 1, vars.iterator()));
        Iterator<String[]> votesAndCounts = cf.recoverAnswers(response);

        while ((votesAndCounts.hasNext())) {
            String[] option = votesAndCounts.next();
            if (!poll.registerOption(option[0])) break;
            for (int i = 0; i<Integer.parseInt(option[1]); i++) {
                poll.registerVote(option[0]);
            }
        }
    }
}
