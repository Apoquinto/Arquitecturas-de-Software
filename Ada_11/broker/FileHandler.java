import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> content = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("File " + fileName + " not found.");
        }

        return content;
    }

    public void writeFile(String fileName, ArrayList<String> content) {
        try {
            if (content.size() > 0) {
                File file = new File(fileName);
                FileWriter writer = new FileWriter(file);

                for (String line : content) {
                    writer.write(line + "\n");
                }

                writer.close();
            }
        } catch (Exception e) {
            System.out.println("File " + fileName + " not found.");
        }
    }
}