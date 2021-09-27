package src.logic;

import java.util.ArrayList;
import java.util.Scanner;

import src.data.FileHandler;
import src.filters.FilterStepWords;
import src.filters.GenerateVariations;
import src.filters.ToLowerCase;
import src.pipe.Pipeline;

public class LogicModule {
    private FileHandler fileHandler;
    private Scanner reader;

    public LogicModule(){
        this.fileHandler = new FileHandler();
        this.reader = new Scanner(System.in);
    }

    public void run(){
        System.out.print(">> ");
        String[] command = reader.nextLine().split(" ");
        
        if (command[0].equals("kwic") && command.length == 3) {
            String inputFileName = command[1];
            String stopWordsFileName = command[2];
            String outputFileName = "output.txt";
            
            ArrayList<String> lines = fileHandler.readFile(inputFileName);
            ArrayList<String> result = new ArrayList<>();
        
            Pipeline pipeline = new Pipeline();
            pipeline.addFilter(new ToLowerCase());
            pipeline.addFilter(new FilterStepWords(stopWordsFileName));
            pipeline.addFilter(new GenerateVariations());
    
            for (String text : lines) {
                ArrayList<String> variations = (ArrayList<String>) pipeline.send(text);
                for (String variation : variations) {
                    result.add(variation);
                }
                result.add("\n\n");
            }

            fileHandler.writeFile(outputFileName, result);
        } else {
            System.out.println("Comando desconocido, revise que este correctamente escrito");
        }
    }
}
 