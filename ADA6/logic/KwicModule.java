package logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

public class KwicModule {
    public KwicModule(){}

    public ArrayList<String> executeKwic(String sentence, HashSet<String> stopWords){
        return generateVariations(normaliceSentence(sentence, stopWords));
    }

    private ArrayList<String> normaliceSentence(String sentence, HashSet<String> stopWords){
        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : sentence.toLowerCase().split(" ")) {
            if(!stopWords.contains(word)) filteredWords.add(word);           
        }
        filteredWords.remove(0);
        return filteredWords;
    }

    private ArrayList<String> generateVariations(ArrayList<String> words){
        ArrayList<String> orderedList = new ArrayList<>();
        String initialSentence = String.join(" ", words); 
        for(int i = 0; i < words.size(); i++){
            String nextSentence = String.join(" ", words.subList(i, words.size())) + " " 
                                + String.join(" ", words.subList(0, i));
            orderedList.add(nextSentence);
            if(nextSentence.equals(initialSentence)) break;
        }
        Collections.sort(orderedList);
        return orderedList;
    }
}