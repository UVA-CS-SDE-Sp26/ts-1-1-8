import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CipherTool {

    private String actual;
    private String cipher;

    public CipherTool() {

        File file = new File("ciphers" + File.separator + "key.txt");

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Key file not found.");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            actual = reader.readLine();  // first line = actual
            cipher = reader.readLine();  // second line = cipher

            reader.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read key file.");
        }

        if (actual == null || cipher == null) {
            throw new IllegalArgumentException("Key file must contain exactly two lines.");
        }

        if (actual.length() != cipher.length()) {
            throw new IllegalArgumentException("Key lines must be the same length.");
        }
    }

    public String decipher(String cipheredText) {

        if (cipheredText == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cipheredText.length(); i++) {

            char ch = cipheredText.charAt(i);
            int index = cipher.indexOf(ch);

            if (index == -1) {
                result.append(ch);
            } else {
                result.append(actual.charAt(index));
            }
        }

        return result.toString();
    }
}