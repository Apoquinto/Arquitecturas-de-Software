package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Rober
 */
public class FileHandler {
    private Logger logger;
    
    public FileHandler(){
        this.logger = Log.getLogger();
    }

    public ArrayList<String> readFile(String fileName) {
        logger.info("");
        ArrayList<String> content = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null){
                content.add(line);
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Archivo " + fileName + " no encontrado");
        }

        return content;
    }

    public void writeFile(String fileName, ArrayList<String> content) {
        logger.info("");
        try {
            if (content.size() > 0) {
                File file = new File(fileName);
                FileWriter writer = new FileWriter(file);

                for (String line : content) {
                    writer.write(line + "\n");
                }

                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Archivo " + fileName + " no encontrado");
        }
    }
}