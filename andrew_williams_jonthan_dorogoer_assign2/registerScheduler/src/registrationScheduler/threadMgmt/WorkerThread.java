package registrationScheduler.threadMgmt;
import registrationScheduler.results.Student;

import registrationScheduler.util.Constants;
import registrationScheduler.results.Results;
import registrationScheduler.util.Logger;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.results.StdoutDisplayInterface;

public class WorkerThread implements Runnable {
    private FileProcessor          fileProcessor;
    private StdoutDisplayInterface resultsInterface;
    private ObjectPool             classPool;

/**
 *
 *Constructor for worker thread
 *
 *@param fp gives the worker thread a file processor
 *@param r gives the worker a  StdoutInterface
 *@param cp give the worker a object pool
 */
    public WorkerThread (FileProcessor fp, StdoutDisplayInterface r, ObjectPool cp) {
	Logger.writeMessage ("Instantiating a WorkerThread", Logger.DebugLevel.CONSTRUCTOR);
	
	fileProcessor = fp;
	resultsInterface = r;
	classPool = cp;
    }

/**
 *
 *Run method for worker thread, needed to implement a Runnable subclass.
 *Takes student from a file 1 line at a time and puts them into a class.
 *If student can't get in 5 classes, kick and try again.
 *Resulting classes are added after each student is placed in a class.
 *
 */
    public void run() {
	Logger.writeMessage (Thread.currentThread().getName() + " began its run method", Logger.DebugLevel.RUN_THREAD);
	Results results = (Results) resultsInterface;

	Student curStudent;
	StringBuilder inputStringBuilder = new StringBuilder("");
	String inputLine = "";
    	
	while (fileProcessor.getNextLine(inputStringBuilder)) {
	    inputLine = inputStringBuilder.toString();
	    curStudent = new Student(inputLine);
	    
	    //try to enroll Student in COURSES_PER_STUDENT number of classes
	    int curPref = -1;
	    int curClass;
	    while (curStudent.numClasses() < Constants.COURSES_PER_STUDENT) {
		curClass = curStudent.nextDesiredClass(curPref);
		if (curClass == -1) break;
		if (classPool.getSeat(curClass)) {
		    curStudent.enrollInClass(curClass);
		}
		curPref = curStudent.getPrefByClass(curClass);
	    }
	    
	    //If we were unable to assign COURSES_PER_STUDENT number of classes, we need to kick someone out of a full class  
	    while (curStudent.numClasses() < Constants.COURSES_PER_STUDENT) {
		curClass = curStudent.nextDesiredClass(-1);
		int openClass = -1;
		for (int i = 0; i < Constants.NUM_COURSES; ++i) {
		    if (!classPool.isFull(i)) {
			openClass = i;
			break;
		    }
		}
	       
		Student studentToKick = results.findStudentToKick(curClass, openClass);
		studentToKick.dropClass(curClass);
		curStudent.enrollInClass(curClass);
		classPool.getSeat(openClass);
		studentToKick.enrollInClass(openClass);
	    }
	    results.addStudent(curStudent);
	}

    }

}
