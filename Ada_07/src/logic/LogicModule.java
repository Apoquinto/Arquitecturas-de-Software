package src.logic;

import java.util.ArrayList;
import java.util.HashSet;

import src.data.FileHandler;

public class LogicModule {
    private FileHandler fileHandler;

    public LogicModule(){
        this.fileHandler = new FileHandler();
    }

    public HashSet<String> getStopWords(){
        return new HashSet<String>(fileHandler.readFile("stopwords.txt"));
    }

    public void run(){
        ArrayList<String> text = fileHandler.readFile("texto.txt");

        fileHandler.writeFile("output.txt", text);
    }
}
 