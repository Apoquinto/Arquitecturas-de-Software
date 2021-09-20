package data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataModule implements DataLayer {
    
    String fileName = "stopwords.txt";
    public String[] retrieveStopWords(){   
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
            Logger.getLogger(DataModule.class.getName()).log(Level.SEVERE, null, ex);
            lines.clear();
        }

        return lines.toArray(new String[lines.size()]);
    }
    
}

