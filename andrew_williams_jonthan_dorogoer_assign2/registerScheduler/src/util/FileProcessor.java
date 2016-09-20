import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {
    private File inputFile       = null;
    private Scanner inputScanner = null;

    public FileProcessor (String fileName) {
	try {
	    inputFile = new File(fileName);
	    inputScanner = new Scanner(inputFile);
	} catch (FileNotFoundException e) {
	    System.out.printf("ERROR: Input file %s not found.  Program halting.\n", fileName);
	    System.exit(1);
	}
    }

    public boolean hasNextLine() {
	return inputScanner.hasNextLine();
    }

    public String getNextLine() {
	return inputScanner.nextLine();
    }

}
