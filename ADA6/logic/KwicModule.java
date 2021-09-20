package logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

public class KwicModule {
    public ArrayList<String> executeKwic(String sentence, HashSet<String> stopWords){
        return generateVariations(normaliceSentence(sentence, stopWords));
    }

    private ArrayList<String> normaliceSentence(String sentence, HashSet<String> stopWords){
        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : sentence.toLowerCase().split(" ")) {
            if(!stopWords.contains(word)) filteredWords.add(word);           
        }
        filteredWords.remove(0); // remove "kwic" word
        return filteredWords;
    }

    private ArrayList<String> generateVariations(ArrayList<String> words){
        ArrayList<String> orderedList = new ArrayList<>();
        String initialSentence = String.join(" ", words);
        orderedList.add(initialSentence);
        for(int i = 1; i < words.size(); i++){
            String nextSentence = String.join(" ", words.subList(i, words.size())) 
                                + " " 
                                + String.join(" ", words.subList(0, i));
            if(nextSentence.equals(initialSentence)) break;
            orderedList.add(nextSentence);
        }
        Collections.sort(orderedList);
        return orderedList;
    }
}