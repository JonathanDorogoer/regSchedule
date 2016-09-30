package registrationScheduler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {
    private File inputFile       = null;
    private Scanner inputScanner = null;

    public FileProcessor (String fileName) {
	Logger.writeMessage ("Instantiating a FileProcessor", Logger.DebugLevel.CONSTRUCTOR);

	try {
	    inputFile = new File(fileName);
	    inputScanner = new Scanner(inputFile);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    public synchronized boolean hasNextLine() {
	return inputScanner.hasNextLine();
    }

    public synchronized boolean getNextLine(StringBuilder retString) {
	retString.setLength(0);  //clear out last input line
	if (!inputScanner.hasNextLine()) {
	    retString.append("");
	    return false;
	}
	retString.append(inputScanner.nextLine());
	return true;
    }

}
