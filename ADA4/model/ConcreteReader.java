/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/* It takes String fileName, return an Array of content
@param String fileName
@return String[]
*/
public class ConcreteReader implements ReaderInterface {
    
    public String[] readFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();
        } catch (Exception ex) {
            Logger.getLogger(ConcreteReader.class.getName()).log(Level.SEVERE, null, ex);
            lines.clear();
        }

        return lines.toArray(new String[lines.size()]);
    }
}
