package service;

import java.util.ArrayList;

public class ServiceModule {
    private FileHandler fileHandler;
    private ArrayList<String[]> archivos;
    private ArrayList<String[]> contenido;

    public ServiceModule(){
        fileHandler = new FileHandler();
        archivos = fileHandler.readFile("archivos/indices.txt");
    }

    public String executeTask(String message) {
        String output = message + ": ";
        try {
            contenido = fileHandler.readFile("archivos/" + archivos.get(Integer.valueOf(message) -1)[1]);
            //In case the content uses ","
            for(String[] lines : contenido){
                for(String line : lines ){
                    output = output + line;
                }
            }
        } catch (Exception e) {
            output = "No se encontró el indice " + message;
        }
        return output;
    }
}
