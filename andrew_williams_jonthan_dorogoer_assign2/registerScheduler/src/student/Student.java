import java.util.ArrayList;

public class Student {
    private final int NUM_CLASSES = 7;

    private int[] prefs                 = new int[NUM_CLASSES];
    private boolean[] classesEnrolledIn = new boolean[NUM_CLASSES];

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
}
