package presentation;
import logic.LogicLayer;
import logic.LogicModule;

import java.util.ArrayList;
import java.util.Scanner;

public class PresentationModule implements PresentationLayer {
    private String greetingMsg;
    private String optionMsg;
    private String exitMsg;
    private ColorConfiguration config;
    private LogicLayer logicLayer;
    private Scanner reader;

    public PresentationModule(ColorConfiguration config){
        this.greetingMsg = "Bienvenido al programa KWIC, elaborado por:\n Cruz Morales Israel\n Gómez Benítez Jonathan\n LLanes Montero Roberto\n Meza Magaña Joshua";
        this.optionMsg = "\nPara poner en marcha el algoritmo, escriba la palabra 'kwic' seguida de una frase, por ejemplo, kwic La casa AzUL es muy GRANDE";
        this.exitMsg = "Gracias por usar el programa KWIC";
        this.config = config;
        this.logicLayer = new LogicModule();
        this.reader = new Scanner(System.in);
    }

    private void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String read() {
        System.out.print(">> ");
        return reader.nextLine();
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    @Override
    public void commonMsg(String msg) {
        print(config.getMainColor() + msg + config.getResetColor());
    }

    @Override
    public void successMsg(String msg) {
        print(config.getSuccessColor() + msg + config.getResetColor());
    }

    @Override
    public void warningMsg(String msg) {
        print(config.getWarningColor() + msg + config.getResetColor());
    }

    @Override
    public void dangerMsg(String msg) {
        print(config.getDangerColor() + msg + config.getResetColor());
    }

    @Override
    public void run() {
        commonMsg(greetingMsg);
        commonMsg(optionMsg);
        String msg = read();
        if(logicLayer.verifyIsKwicCommand(msg)){
            ArrayList<String> combinations = logicLayer.executeKwic(msg);
            for(String combination : combinations){
                successMsg(combination);
            }
            commonMsg(exitMsg);
        } else {
            this.exitMsg = "Error, no introdujo una sentencia 'kwic', ejecute e intente de nuevo";
            warningMsg(exitMsg);
        }
    }
}
