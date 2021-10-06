package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Rober
 */
public class FileHandler {
    public FileHandler(){}

    public ArrayList<String> readFile(String FileName){
        ArrayList<String> content = new ArrayList<>();

        try {
            File file = new File(FileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null){
                content.add(line);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }

        return content;
    }

    public void writeFile(String fileName, ArrayList<String> content) {
        try {
            if (content.size() > 0) {
                File csv = new File(fileName);
                FileWriter writter = new FileWriter(csv);

                for (String line : content) {
                    writter.write(line + "\n");
                }

                writter.close();
            }
        } catch (Exception e) {
            System.out.println("Error mientra se trataba de escribir el archivo " + fileName);
            e.printStackTrace();
        }
    }
}