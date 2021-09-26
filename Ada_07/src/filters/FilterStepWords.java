package src.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import src.data.FileHandler;

public class FilterStepWords extends Filter {
    private FileHandler fileHandler;
    
    public FilterStepWords(){
        this.fileHandler = new FileHandler();
    }
    
    @Override
    protected Object process(Object input) {
        ArrayList<String> filteredWords = new ArrayList<String>();
        HashSet<String> stopWords = new HashSet<>(Arrays.asList(fileHandler.readFile("stopwords.txt").split(" ")));

        for (String word : input.toString().split(" ")) {
            if(!stopWords.contains(word)) filteredWords.add(word);           
        }

        return filteredWords;
    }
    
}
