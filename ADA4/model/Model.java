/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author israc
 */
public class Model {
    private final String fileName;
    private final String teamString;

    public Model() {
        this.fileName = "Nombres.txt";
        this.teamString = "Cruz Morales Israel\n" + "Gomez Benitez Jonathan\n" + "Llanes Montero Roberto\n" + "Meza Magaña Joshua\n\n\n"; 
    }
    
    public String getTeamString(){
        return teamString;
    }
    
    public String[] readFile() {
        ReaderInterface fileReader = new ConcreteReader();
        return fileReader.readFile(fileName);
         
    }
}
