package logic;

import java.util.ArrayList;

import data.*;

public class LogicModule implements LogicLayer {
    private DataLayer dataLayer;
    private KwicModule kwic;

    public LogicModule(){
        this.dataLayer = new DataModule();
        this.kwic = new KwicModule();
    }

    public boolean verifyIsKwicCommand(String msg) {
        return msg.split(" ")[0].equals("kwic");
    }

    public ArrayList<String> executeKwic(String sentence) {
        return kwic.executeKwic(sentence, dataLayer.getStopWords());
    }
}
