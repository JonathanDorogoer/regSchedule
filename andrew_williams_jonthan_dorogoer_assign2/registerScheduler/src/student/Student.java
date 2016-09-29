import java.util.ArrayList;

public class Student {
    private final int NUM_CLASSES = 7;

    private int[] prefs                 = new int[NUM_CLASSES];
    private boolean[] classesEnrolledIn = new boolean[NUM_CLASSES];

    private int[] classPrefs           = new int[NUM_CLASSES];

    /*
    public Student(int pref0, int pref1, int pref2, int pref3, int pref4, int pref5, int pref6) {
	prefs[0] = pref0;
	prefs[1] = pref1;
	prefs[2] = pref2;
	prefs[3] = pref3;
	prefs[4] = pref4;
	prefs[5] = pref5;
	prefs[6] = pref6;
	
	for (int i = 0; i < NUM_CLASSES; ++i)
	    classesEnrolledIn[i] = false;


	 
	
    }
    */

    public Student (string inputLine) {
	String temp[] = inputLine.split("\\s+");
	
	for (int i = 1; i <= 7; ++i)
	    prefs[i - 1] = Integer.parseInt(temp[i]);

	for (int i = 0; i < NUM_CLASSES; ++i)
	    classesEnrolled[i] = false;

	for (int i = 1; i < NUM_CLASSES; ++i) {
	    classPref[Integer.parseInt(temp[i]) - 1] = i;
	}
    }

    public void dropClass (int classNum) {
	assert (classNum <= NUM_CLASSES);
	classesEnrolledIn[classNum - 1] = false;
    }

    public void enrollInClass (int classNum) {
	assert (classNum <= NUM_CLASSES);
	classesEnrolledIn[classNum - 1] = true;
    }

    public String getClasses() {
	String retVal = new String();

	for (int i = 0; i < NUM_CLASSES; ++i) {
	    if (classesEnrolledIn[i] == true)
		retVal = retVal + (i + 1) + ' ';
	}

	return retVal;
    }

    public int numClasses() {
	int retVal = 0;
	for (int i = 0; i < NUM_CLASSES; ++i) {
	    if (classesEnrolledIn[i])
		++retVal;
	}

	return retVal;
    }

    public int nextDesiredClass() {
	for (int i = 0; i < NUM_CLASSES; ++i) {
	    if (classesEnrolledIn[classPrefs[i])
		return i + 1;
	}
    }
}