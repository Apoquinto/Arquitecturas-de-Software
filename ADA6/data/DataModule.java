package data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataModule implements DataLayer {
    private String fileName;
    
    public DataModule(){
        this.fileName = "stopwords.txt";
    }

    public HashSet<String> getStopWords(){   
        HashSet<String> stopWords = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stopWords.add(line);
            }

            bufferedReader.close();
        } catch (Exception ex) {
            Logger.getLogger(DataModule.class.getName()).log(Level.SEVERE, null, ex);
            stopWords.clear();
        }

        return stopWords;
    }
    
}

