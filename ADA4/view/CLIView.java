package view;

import java.util.Scanner;

public class CLIView implements ICLI {
    private CLIConfig config;
    private Scanner reader;

    public CLIView(CLIConfig config){
        this.config = config;
        this.reader = new Scanner(System.in);
    }

    @Override
    public void update(String[] list) {
        printList(list);
    }

    private void printList(String[] list){
        for (String element : list) {
            print(element);
        }
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String read(String desc) {
        print(desc);
        System.out.print(">> ");
        return reader.nextLine();
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
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

}
