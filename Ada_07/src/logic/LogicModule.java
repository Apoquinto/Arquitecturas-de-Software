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
        if( command[0].equals("kwic") && command.length < 4){
            String outputFileName = command[2];
            String inputFileName = command[1];
            
            String text = fileHandler.readFile(inputFileName);
        
            Pipeline pipeline = new Pipeline();
            pipeline.addFilter(new ToLowerCase());
            pipeline.addFilter(new FilterStepWords());
            pipeline.addFilter(new GenerateVariations());
    
            ArrayList<String> result = (ArrayList<String>) pipeline.send(text);

            fileHandler.writeFile(outputFileName, result);
        }
        else {
            System.out.println("Comando desconocido, revise que este correctamente escrito");
        }
    }
}
 