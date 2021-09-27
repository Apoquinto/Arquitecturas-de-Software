package src.filters;

import java.util.ArrayList;

import src.data.FileHandler;

public class FilterStepWords extends Filter {
    private ArrayList<String> stopWords;
    
    public FilterStepWords(String stopWordsFileName){
        this.stopWords = new FileHandler().readFile(stopWordsFileName);
    }
    
    @Override
    protected Object process(Object input) {
        ArrayList<String> filteredWords = new ArrayList<String>();

        for (String word : input.toString().split(" ")) {
            if(!stopWords.contains(word)) filteredWords.add(word);           
        }

        return filteredWords;
    }
}
