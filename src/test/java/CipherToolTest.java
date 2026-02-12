import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class CipherToolTest {

    private final File keyFile = new File("ciphers/key.txt");

    private String saveOriginalKey() {
        if (!keyFile.exists()) return null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(keyFile));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            return null;
        }
    }

    private void restoreOriginalKey(String original) {
        if (original == null) {
            keyFile.delete();
            return;
        }

        try {
            FileWriter writer = new FileWriter(keyFile);
            writer.write(original);
            writer.close();
        } catch (IOException e) {
            fail("Could not restore original key.");
        }
    }

    private void writeTempKey(String line1, String line2) {
        try {
            FileWriter writer = new FileWriter(keyFile);
            writer.write(line1 + "\n");
            writer.write(line2);
            writer.close();
        } catch (IOException e) {
            fail("Test setup failed.");
        }
    }

    @Test
    public void testDecipherValid() {

        String original = saveOriginalKey();
        writeTempKey("abc", "bca");
        CipherTool tool = new CipherTool();
        String result = tool.decipher("bca");

        assertEquals("abc", result);

        restoreOriginalKey(original);
    }

    @Test
    public void testInvalidKeyLength() {

        String original = saveOriginalKey();
        writeTempKey("abc", "bc");
        assertThrows(IllegalArgumentException.class, CipherTool::new);
        restoreOriginalKey(original);
    }

    @Test
    public void testNonMappedCharactersRemain() {

        String original = saveOriginalKey();
        writeTempKey("abc", "bca");
        CipherTool tool = new CipherTool();
        String result = tool.decipher("b! c");

        assertEquals("a! b", result);

        restoreOriginalKey(original);
    }
}