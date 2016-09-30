package registrationScheduler.result;
import java.util.ArrayList;
import registrationScheduler.util.Constants;
import registrationScheduler.util.Logger;
public class Student {
    private int[]     classNumByPref    = new int[Constants.NUM_COURSES];
    private int[]     prefByClassNum    = new int[Constants.NUM_COURSES];
    private boolean[] classesEnrolledIn = new boolean[Constants.NUM_COURSES];
    
    private String name;
/**
 *Constructor for a new student
 *@param inputLine Creates a student based on 1 line of input from the file
 */
    public Student (String inputLine) {
	Logger.writeMessage ("Instantiating a Student", Logger.DebugLevel.CONSTRUCTOR);

	String temp[] = inputLine.split("\\s+");
	
	name = temp[0];

	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    classesEnrolledIn[i] = false;
	    prefByClassNum[i] = Integer.parseInt(temp[i + 1]) - 1;
	}
	    
	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    classNumByPref[prefByClassNum[i]] = i;
	}

    }
/**
 *
 *Drops a class from a student, needed for kicking phase in algorithm
 *@param classNum give class to drop
 */
    public void dropClass (int classNum) {
	classesEnrolledIn[classNum ] = false;
    }
/**
 *
 *Enrolls a student into a class, needed for the kicking phase of the algorithm
 *@param classNum number of class to enroll in
 */
    public void enrollInClass (int classNum) {
	classesEnrolledIn[classNum] = true;
    }
/**
 *Gives how many classes a student is enrolled in
 *
 *@return how many classes student is enrolled in
 */
    public int numClasses() {
	int retVal = 0;
	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    if (classesEnrolledIn[i])
		++retVal;
	}

	return retVal;
    }
/**
 *Gets class from preffered order sorted array
 *@param classNum class number for preffered class
 */
    public int getPrefByClass (int classNum) {
	return prefByClassNum[classNum];
    }
/**
 *Checks if enrolled in particular class
 *@param classNum class number to check
 *@return true or false depending on if enrolled in class
 */
    public boolean isEnrolled (int classNum) {
	return classesEnrolledIn[classNum];
    }
/**
 *Checks if not enrolled in particular class
 *@param classNum class number to check
 *@return true or false depending on if not in rolled in class
 */
    public boolean isNotEnrolled (int classNum) {
	return !classesEnrolledIn[classNum];
    }

/**
 *takes in a preference score, and returns the class 
 *number of the class with the next highest
 *preference score that student in not enrolled in
 *if student is already enrolled in all classes more desirable than inPrefScore, 
 *-1 is returned
 *@param inPrefScore index for preferrence score available for the student
 *@return returns next available peferred class
 */
    public int nextDesiredClass(int inPrefScore) {
	int curPrefScore = inPrefScore + 1;
	while (curPrefScore < Constants.NUM_COURSES) {
	    if ( !classesEnrolledIn[classNumByPref[curPrefScore]])
		return classNumByPref[curPrefScore];
	    ++curPrefScore;
	}

	return -1;
    }
/**
 *
 *
 *@return gets student name
 */
    public String getName() {
	return name;
    }
/**
 *
 *
 *@return returns a string of Student followed by classes enrolled followed by totalPrefScore
 */
    public String toString(){
	int totalPrefScore = 0;
        String retVal = name;
        
	retVal += " Enrolled in classes: ";
        for (int i = 0; i < Constants.NUM_COURSES; i++) {
	    if (classesEnrolledIn[i]) {
		int x = i+1;
		retVal += " " + x;
		totalPrefScore += prefByClassNum[i] + 1;
	    }
        }
	retVal += " Aggregate Preference Score: " + totalPrefScore;

        return retVal;
    }
/**
 *
 *
 *@return gets the total preferrence score
 */
    public int getTotalPrefScore() {
	int totalPrefScore = 0;
	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    if (classesEnrolledIn[i])
		totalPrefScore += prefByClassNum[i] + 1;
	}
	return totalPrefScore;
    }
    
}
