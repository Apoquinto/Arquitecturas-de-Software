package src.filters;

import java.util.ArrayList;
import java.util.Collections;

public class GenerateVariations extends Filter{

    @Override
    protected Object process(Object input) {
        ArrayList <String> orderedList = new ArrayList<>();
        ArrayList <String> words = (ArrayList<String>) input;
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
