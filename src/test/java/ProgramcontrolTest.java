import org.junit.jupiter.api.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class ProgramcontrolTest {
    //check if I get my list of files, make sure length of list greater than 0,
    // check if name of file is there
    //make sure the contents of 01 is whats in filea.txt

    static class StubFileHandler extends FileHandler {
        @Override
        public String[] listFiles() {
            return new String[]{"filea.txt", "fileb.txt", "filec.txt"};
        }

        @Override
        public String readFile(String filename) {
            if ("filea.txt".equals(filename)) return "A";
            if ("fileb.txt".equals(filename)) return "B";
            if ("filec.txt".equals(filename)) return "C";
            return null;
        }
    }

    @Test
    public void testNumToFile(){ //check to make sure the number is correctly assigned to file
        FileHandler handler = new StubFileHandler();
        Programcontrol object = new Programcontrol(handler);
        assertEquals("filec.txt", object.numToFile(3));
    }

    @Test
    //length not 0
    public void testGetFiles(){
        FileHandler handler = new StubFileHandler();
        Programcontrol fileobj = new Programcontrol(handler);
        String[] lists = fileobj.getFileList();
        assertNotEquals(0,lists.length);
    }

    @Test
    //length not 0
    public void testFileName(){
        FileHandler handler = new StubFileHandler();
        Programcontrol fileobj = new Programcontrol(handler);

        String[] lists = fileobj.getFileList();
        assertTrue(Arrays.asList(lists).contains("filea.txt"));
    }

    @Test
    void testFileContentsIsValid(){
        FileHandler handler = new StubFileHandler();
        Programcontrol tester = new Programcontrol(handler);
        String results = tester.fileContents(1);
        assertNotNull(results);
        assertEquals("A", results);
    }
}
