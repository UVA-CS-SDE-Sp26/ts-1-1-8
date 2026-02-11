import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TopSecretTest {

    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testNoArgumentsListsFiles() {
        TopSecret.main(new String[]{});
        String output = outputStream.toString();
        assertTrue(output.contains("01") || output.contains("No files"));
    }

    @Test
    void testInvalidArgumentCount() {
        TopSecret.main(new String[]{"1", "2", "3"});
        String output = outputStream.toString();
        assertTrue(output.contains("Error"));
    }

    @Test
    void testNonNumericFileNumber() {
        TopSecret.main(new String[]{"abc"});
        String output = outputStream.toString();
        assertTrue(output.contains("Error"));
    }

    @Test
    void testInvalidFileNumber() {
        TopSecret.main(new String[]{"999"});
        String output = outputStream.toString();
        assertTrue(output.contains("Error"));
    }

    @Test
    void testValidFileNumber() {
        TopSecret.main(new String[]{"1"});
        String output = outputStream.toString();
        assertFalse(output.contains("Invalid number of arguments"));
    }
}

