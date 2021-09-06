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
       String separados[] = null ;
       for (int i = 0; i < namesArray.length; i++) {
        namesArray[i] = namesArray[i].toLowerCase();
        separados = namesArray[i].split(" ");
        
        for (int j = 0; j < separados.length;j++){
            String upper = null;
            upper = separados[j].substring(0,1).toUpperCase();
            separados[j] = upper + separados[j].substring(1); 
            upper = "";
            if (separados.length > 1){
                namesArray[i]= separados[0] + " " + separados[1];
            }else{
                namesArray[i] = separados[0];
            }
        }
        
        
        }
        for (int i = 0; i < namesArray.length; i++){
       System.out.println(namesArray[i]);
        }
        return null;
    }
     
}
