import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class ProgramcontrolTest {
    //check if I get my list of files, make sure length of list greater than 0,
    // check if name of file is there
    //make sure the contents of 01 is whats in filea.txt

    @Test
    public void testNumToFile(){
        FileHandler handler = new FileHandler();
        Programcontrol object = new Programcontrol(handler);
        assertEquals("filec.txt", object.numToFile(3));
    }

    @Test
    //length not 0
    public void testGetFiles(){
        FileHandler handler = new FileHandler();
        Programcontrol fileobj = new Programcontrol(handler);
        String[] lists = fileobj.getFileList();
        assertNotEquals(0,lists.length);
    }

    @Test
    //length not 0
    public void testFileName(){
        FileHandler handler = new FileHandler();
        Programcontrol fileobj = new Programcontrol(handler);

        String[] lists = fileobj.getFileList();
        assertTrue(Arrays.asList(lists).contains("filea.txt"));
    }
}
