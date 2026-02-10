import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileHandlerTest {

    private void clearDataFolder() {
        File dataDir = new File("data");
        if (dataDir.exists()) {
            File[] files = dataDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }
    }

    @Test
    public void testListFiles() {
        clearDataFolder();
        File dataDir = new File("data");
        dataDir.mkdir();

        try {
            new File(dataDir, "file1.txt").createNewFile();
            new File(dataDir, "file2.txt").createNewFile();
        } catch (IOException e) {
            fail("Setup failed");
        }

        FileHandler handler = new FileHandler();
        String[] files = handler.listFiles();

        assertEquals(2, files.length);
        assertTrue(files[0].equals("file1.txt") || files[1].equals("file1.txt"));
        assertTrue(files[0].equals("file2.txt") || files[1].equals("file2.txt"));
    }

    @Test
    public void testReadFile() {
        clearDataFolder();

        File dataDir = new File("data");
        dataDir.mkdir();

        File testFile = new File(dataDir, "mission.txt");

        try {
            FileWriter writer = new FileWriter(testFile);
            writer.write("SECRET DATA");
            writer.close();
        } catch (IOException e) {
            fail("Setup failed");
        }

        FileHandler handler = new FileHandler();
        String result = handler.readFile("mission.txt");

        assertNotNull(result);
        assertTrue(result.contains("SECRET DATA"));
    }

    @Test
    public void testMissingFile() {
        clearDataFolder();

        FileHandler handler = new FileHandler();
        String result = handler.readFile("missing.txt");

        assertNull(result);
    }

    @Test
    public void testEmptyDirectory() {
        clearDataFolder();

        File dataDir = new File("data");
        dataDir.mkdir();

        FileHandler handler = new FileHandler();
        String[] files = handler.listFiles();

        assertEquals(0, files.length);
    }
}
