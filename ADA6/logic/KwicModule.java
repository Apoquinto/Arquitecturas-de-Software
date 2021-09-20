package logic;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

public class KwicModule {
    public KwicModule(){}

    public ArrayList<String> makeKwic(String sentence){
        return generateVariations(normaliceSentence(sentence));
    }

    private String[] normaliceSentence(String sentence){
        return sentence.toLowerCase().split(" ");
    }

    private ArrayList<String> generateVariations(String[] words){
        ArrayList<String> orderedList = new ArrayList<>();
        String initialSentence = String.join(" ", words); 
        for(int i = 0; i < words.length; i++){
            String nextSentence = String.join(" ", Arrays.copyOfRange(words, i, words.length)) + 
                                " " + String.join(" ", Arrays.copyOfRange(words, 0, i));
            if(!nextSentence.equals(initialSentence)){
                orderedList.add(nextSentence);
            }
            else{
                break;
            }
        }
        Collections.sort(orderedList);
        return orderedList;
    }
}