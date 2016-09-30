package registrationScheduler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {
    private File inputFile       = null;
    private Scanner inputScanner = null;
/**
 *Opens file with given name and check for any problems.
 *
 *@param fileName name of file to process
 */
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
/**
 *Checks for any remaining lines in the file
 *
 *@return true or false if line exists
 */
    public synchronized boolean hasNextLine() {
	return inputScanner.hasNextLine();
    }

/**
 *Gets next line in the file
 *@param retString passes a string to append next line to.
 *@return true or false if the next line exists
 */
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
