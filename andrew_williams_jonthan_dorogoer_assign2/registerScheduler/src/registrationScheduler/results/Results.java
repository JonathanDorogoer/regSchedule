package registrationScheduler.results;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Constants;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {
    ArrayList<Student> students = new ArrayList<Student>();
    /**
     *
     *Constructor for Results Interface
     *
     */
    public Results() {
	Logger.writeMessage ("Instantiating a Results", Logger.DebugLevel.CONSTRUCTOR);
    }

    /**
     * 
     *@return gives a list of student results in string format
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();
	
	for (Student s : students) {
	    sb.append(s.toString());
	    sb.append("\n");
	}
	//get rid of the trailing \n
	if (sb.length() > 0)
	    sb.setLength(sb.length() - 1);

	return sb.toString();
    }

    /**
     *Prints students schedules along with their preferrence scores
     *Also prints number of students in each class
     */
    public synchronized void writeSchedulesToScreen() {
	int numStudentsInClass[] = new int[Constants.NUM_COURSES];

	for (int i = 0; i < Constants.NUM_COURSES; ++i)
	    numStudentsInClass[i] = 0;

	for (Student s : students) {
	    System.out.printf("%s\n", s.toString());
	    for (int i = 0; i < Constants.NUM_COURSES; ++i) {
		if (s.isEnrolled(i))
		    numStudentsInClass[i] += 1;
	    }
	}
	
	for (int i = 0; i < Constants.NUM_COURSES; ++i)
	    System.out.printf("Class %d has %d students enrolled.\n", i + 1, numStudentsInClass[i]);
    }

    /**
     * Prints to a specified file what class each student is taking.
     * Prints to a specified file how many students are in each class.
     * Prints to a specified file the average preference score.
     * @param outputFileName name of file to write results to (will be created if not present and destroyed/overwritten if it is present)
     */
    public void writeSchedulesToFile(String outputFileName) {
	File outputFile            = null;
	PrintWriter outPrintWriter = null;
	int numStudentsInClass[] = new int[Constants.NUM_COURSES];
	
	try {
	    outputFile = new File(outputFileName);
	    outPrintWriter = new PrintWriter(outputFile);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	
	for (int i = 0; i < Constants.NUM_COURSES; ++i)
	    numStudentsInClass[i] = 0;

	for (Student s : students) {
	    outPrintWriter.printf("%s\n", s.toString());
	    for (int i = 0; i < Constants.NUM_COURSES; ++i) {
		if (s.isEnrolled(i))
		    numStudentsInClass[i] += 1;
	    }
	}
	
	for (int i = 0; i < Constants.NUM_COURSES; ++i)
	    outPrintWriter.printf("Class %d has %d students enrolled.\n", i + 1, numStudentsInClass[i]);

	outPrintWriter.printf("The average preference value is %.1f\n", getAveragePrefScore());

	outPrintWriter.close();
    }

    /**
     * adds a new student
     * @param student the student to be added
     */
    public synchronized void addStudent (Student student) {
	Logger.writeMessage ("Adding student " + student.getName() + " to Results Class", Logger.DebugLevel.ENTRY_ADDED);
	students.add(student);
    }

    /**
     * given a class which is full and a class which has seats available, finds and returns a student who is in both
     *@param fullClass a full class
     *@param openClass a open class
     *@return finds and returns a student who is in fullClass but not in openclass
     */
    public synchronized Student findStudentToKick (int fullClass, int openClass) {
	for (Student s : students) {
	    if (s.isEnrolled(fullClass) && s.isNotEnrolled(openClass))
		return s;
	}
	Student s = new Student("");
	return s;
    }


    /**
     *Enrolls a student into a particular class
     *@param student student to enroll
     *@param classNum class to enroll into
     */
    public synchronized void enrollStudentInClass (Student student, int classNum) {
	student.enrollInClass (classNum);
    }
    
    /**
     *Removes a student from a particular class
     *@param student student to remove from class
     *@param classNum class to remove student from
     */
    public synchronized void removeStudentFromClass (Student student, int classNum) {
	student.dropClass (classNum);
    }

    /**
     *gets an average preferrence score from all students in the student list
     *@return Average perferrence score
     */
    public double getAveragePrefScore() {
	double numStudents = 0;
	double totalPrefScore = 0;
	for (Student student : students) {
	    ++numStudents;
	    totalPrefScore += student.getTotalPrefScore();
	}
	return totalPrefScore / numStudents;
    }
}

    
