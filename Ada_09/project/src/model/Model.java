package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

public class Model {
    private final Logger logger;
    private final FileHandler fileHandler;
    private Poll poll;

    public Model() {
        this.logger = Log.getLogger();
        logger.info("");
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

    public Collection<String> getLabels(){
        logger.info("");
        return this.poll.getOptionsNames();
    }

    public Collection<PollOption> getOptionsData(){ return this.poll.getOptions(); }

    public void generateVotesFiles(){
        logger.info("");
        for (String name: poll.getOptionsNames()) {
            fileHandler.writeFile(name + ".txt", poll.getEventsHistoryByName(name));
        }
    }

    public Iterator<PollOption> getOptionsIterator(){
        return poll.getOptions().iterator();
    }

    public void readInfo(String fileName) {
        logger.info("");
        for (String option: fileHandler.readFile(fileName)) {
            this.poll.registerOption(option);
            try{
                logger.info("Se encontro el archivo, cargando datos de " + option);
                Iterator<String> data = fileHandler.readFile(option + ".txt").iterator();
                data.next();
                data.next();
                data.forEachRemaining((vote) -> poll.registerVote(option));
            }
            catch (Exception e){
                logger.info("No se encontro el archivo de " + option + ", generando opción.");
            }
        }
    }
    // At the end of the voting phase, you always have to update the observer!
}
