/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {

        FileHandler handler = new FileHandler();
        Programcontrol control = new Programcontrol(handler);

        if (args.length == 0) {
            listFiles(control);
        }
        else if (args.length == 1) {
            displayFile(control, args[0]);
        }
        else if (args.length == 2) {
            displayFile(control, args[0]);
            // Cipher key will be used later when cipher teammate finishes
        }
        else {
            printError("Invalid number of arguments.");
        }
    }

    private static void listFiles(Programcontrol control) {

        String[] files = control.getFileList();

        if (files.length == 0) {
            System.out.println("No files available.");
            return;
        }

        for (int i = 0; i < files.length; i++) {
            System.out.printf("%02d %s%n", i + 1, files[i]);
        }
    }

    private static void displayFile(Programcontrol control, String fileNumber) {

        int number;

        try {
            number = Integer.parseInt(fileNumber);
        } catch (NumberFormatException e) {
            printError("File number must be numeric.");
            return;
        }

        String content = control.fileContents(number);

        if (content == null) {
            printError("Invalid file number.");
            return;
        }

        System.out.println(content);
    }

    private static void printError(String message) {
        System.err.println("Error: " + message);
    }
}
