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
    private final String fileName; /*Atributo añadido*/
    private final String teamString;

    public Model() { /*Constructor añade el nombre de archivo*/
        this.fileName = "Nombres.txt";
        this.teamString = "Cruz Morales Israel\n" + "Meza Magaña Joshua\n" + "Gomez Benitez Jonathan\n" + "Llanes Montero Roberto\n"; 
    }
    
    public String teamString(){
        return this.teamString;
    }
    
    public String[] readFile() {
        ReaderInterface fileReader = new ConcreteReader();
        return fileReader.readFile(fileName);
         
    }
}
