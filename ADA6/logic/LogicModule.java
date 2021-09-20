package logic;

import java.util.ArrayList;

import data.DataModule;

public class LogicModule {
    private DataModule dataModule;
    private KwicModule kwic;

    public LogicModule(){
        this.dataModule = new DataModule();
        this.kwic = new KwicModule();
    }

    public Boolean verifyIsKwicCommand(String msg){
        String[] command = msg.split(" ");
        Boolean flag = false;
        if(command[0].equals("kwic")) flag = true;
        return flag;
    }

    public ArrayList<String> executeKwic(String sentence){
        return kwic.executeKwic(sentence, dataModule.getStopWords());
    }
}
