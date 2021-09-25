package src;

import java.util.ArrayList;

import src.model.FileHandler;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        FileHandler fileHandler = new FileHandler();

        data = fileHandler.readFile("Data.txt");
        fileHandler.writeFile("test.txt", data);
    }
}