/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
/**
 *
 * @author israc
 */
public class ConcreteParser implements NamesParser{
    
    public String[] parse(String[] namesArray){
        String[] output = new String[namesArray.length];
        
        for (int i = 0; i < namesArray.length; i++) {
            String[] separados = namesArray[i].toLowerCase().split(" ");
            String finalString = "";
            
            for (int j = 0; j < separados.length;j++){
                String firstLetter = separados[j].substring(0,1).toUpperCase();
                separados[j] = firstLetter + separados[j].substring(1);
                finalString += separados[j] + (j + 1 < separados.length ? " " : "");
            }

            output[i] = finalString;
        }
        
        return output;
    }
     
}
