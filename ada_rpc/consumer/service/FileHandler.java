package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {
    public FileHandler(){
    }

    public ArrayList<String[]> readFile(String fileName) {
        ArrayList<String[]> content = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null){
                content.add(line.split(","));
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Archivo " + fileName + " no encontrado");
        }

        return content;
    }

    public void writeFile(String fileName, ArrayList<String[]> content) {
        try {
            if (content.size() > 0) {
                File file = new File(fileName);
                FileWriter writer = new FileWriter(file);

                for (String[] line : content) {
                    int i = 0;
                    for(String value : line){
                        if(i==0){
                            writer.write(value + ",");
                        } else{
                            writer.write(value + "\n");
                        }
                        i++;
                    }
                }

                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Archivo " + fileName + " no encontrado");
        }
    }
}