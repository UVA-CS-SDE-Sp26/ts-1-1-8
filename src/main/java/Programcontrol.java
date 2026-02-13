public class Programcontrol {

    private FileHandler handler;

    public FileHandler getHandler() {
        return handler;
    }

    public void setHandler(FileHandler handler) {
        this.handler = handler;
    }

    public Programcontrol(FileHandler handler) {
        this.handler = handler;
    }

    public String numToFile(int fileNum) { //assigns the numbers to the files
        String[] fileList = handler.listFiles();
        int i = fileNum - 1; //must offset by 1
        if (i < 0 || i >= fileList.length) {
            return null;
        }
        return fileList[i];
    }

    public String[] getFileList(){ //gets the list of files
       return handler.listFiles();
       //listFiles make an array of strings
    }
    public String fileContents(int fileNum){ //returns what is in the file
        String fileName = numToFile(fileNum);
        if(fileName ==null){
            return null;
        }
        return handler.readFile(fileName);
    }

}
