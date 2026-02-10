import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class FileHandler {

    private final String dataFolder = "data";

    // Returns sorted list of filenames in the data folder
    public String[] listFiles() {

        File folder = new File(dataFolder);

        if (!folder.exists() || !folder.isDirectory()) {
            return new String[0];
        }

        String[] fileNames = folder.list();

        if (fileNames == null) {
            return new String[0];
        }

        Arrays.sort(fileNames); // keeps numbering consistent

        return fileNames;
    }

    // Returns entire contents of a file as a String
    public String readFile(String filename) {

        File file = new File(dataFolder, filename);

        if (!file.exists() || !file.isFile()) {
            return null;
        }

        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }

            reader.close();

        } catch (IOException e) {
            return null;
        }

        return content.toString();
    }
}