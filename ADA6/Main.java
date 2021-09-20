import java.util.ArrayList;

import logic.KwicModule;

public class Main {
    public static void main(String[] args) {
        KwicModule kwic = new KwicModule();
        String fakeData = "Todo lo que se, es que no se nada";
        
        ArrayList<String> sentences = kwic.makeKwic(fakeData);

        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}
