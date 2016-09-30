package registrationScheduler.result;
import java.util.ArrayList;
import registrationScheduler.util.Constants;
import registrationScheduler.util.Logger;
public class Student {
    private int[]     classNumByPref    = new int[Constants.NUM_COURSES];
    private int[]     prefByClassNum    = new int[Constants.NUM_COURSES];
    private boolean[] classesEnrolledIn = new boolean[Constants.NUM_COURSES];
    
    private String name;

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

    public void dropClass (int classNum) {
	classesEnrolledIn[classNum ] = false;
    }

    public void enrollInClass (int classNum) {
	classesEnrolledIn[classNum] = true;
    }

    public int numClasses() {
	int retVal = 0;
	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    if (classesEnrolledIn[i])
		++retVal;
	}

	return retVal;
    }

    public int getPrefByClass (int classNum) {
	return prefByClassNum[classNum];
    }

    public boolean isEnrolled (int classNum) {
	return classesEnrolledIn[classNum];
    }

    public boolean isNotEnrolled (int classNum) {
	return !classesEnrolledIn[classNum];
    }


    //takes in a preference score, and returns the class number of the class with the next highest
    //preference score that student in not enrolled in
    //if student is already enrolled in all classes more desirable than inPrefScore, -1 is returned
    public int nextDesiredClass(int inPrefScore) {
	int curPrefScore = inPrefScore + 1;
	while (curPrefScore < Constants.NUM_COURSES) {
	    if ( !classesEnrolledIn[classNumByPref[curPrefScore]])
		return classNumByPref[curPrefScore];
	    ++curPrefScore;
	}

	return -1;
    }

    public String getName() {
	return name;
    }
    
    public String toString(){
	int totalPrefScore = 0;
        String retVal = name;
        
	retVal += " Enrolled in classes:";
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

    public int getTotalPrefScore() {
	int totalPrefScore = 0;
	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    if (classesEnrolledIn[i])
		totalPrefScore += prefByClassNum[i] + 1;
	}
	return totalPrefScore;
    }
    
}
