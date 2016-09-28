import java.util.ArrayList;

public class Student {
    private final int NUM_CLASSES = 7;

    private int[]     classNumByPref    = new int[NUM_CLASSES];
    private int[]     prefByClassNum    = new int[NUM_CLASSES];
    private boolean[] classesEnrolledIn = new boolean[NUM_CLASSES];


    public Student (String inputLine) {
	String temp[] = inputLine.split("\\s+");
	
	for (int i = 0; i < NUM_CLASSES; ++i) {
	    classesEnrolledIn[i] = false;
	    prefByClassNum[i] = Integer.parseInt(temp[i + 1]) - 1;
	}
	    
	for (int i = 0; i < NUM_CLASSES; ++i) {
	    classNumByPref[prefByClassNum[i]] = i;
	}

	System.out.printf("classNumByPref: ");
	for (int i = 0; i < NUM_CLASSES; ++i)
	    System.out.printf("%d ", classNumByPref[i]);
	System.out.printf("\n");

	System.out.printf("prefByClassNum: ");
	for (int i = 0; i < NUM_CLASSES; ++i)
	    System.out.printf("%d ", prefByClassNum[i]);
	System.out.printf("\n");


	/*
	for (int i = 1; i <= 7; ++i)
	    prefs[i - 1] = Integer.parseInt(temp[i]);

	for (int i = 0; i < NUM_CLASSES; ++i)
	    classesEnrolledIn[i] = false;

	for (int i = 1; i <= NUM_CLASSES; ++i) {
	    classPrefs[Integer.parseInt(temp[i]) - 1] = i;
	}
	*/
	/*
	System.out.printf("prefs: %d %d %d %d %d %d %d\n", prefs[0], prefs[1], prefs[2], prefs[3], prefs[4], prefs[5], prefs[6]);
	System.out.printf("classPrefs: %d %d %d %d %d %d %d\n", classPrefs[0], classPrefs[1], classPrefs[2], classPrefs[3], classPrefs[4], classPrefs[5], classPrefs[6]);*/
    }

    public void dropClass (int classNum) {
	assert (classNum < NUM_CLASSES);
	classesEnrolledIn[classNum ] = false;
    }

    public void enrollInClass (int classNum) {
	assert (classNum < NUM_CLASSES);
	classesEnrolledIn[classNum] = true;
    }

    public String getClasses() {
	String retVal = new String();

	for (int i = 0; i < NUM_CLASSES; ++i) {
	    if (classesEnrolledIn[i] == true)
		retVal = retVal + i + ' ';
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
	while (curPrefScore < NUM_CLASSES) {
	    if ( !classesEnrolledIn[classNumByPref[curPrefScore]])
		return classNumByPref[curPrefScore];
	    ++curPrefScore;
	}

	return -1;
	/*while (curDesiredClass < NUM_CLASSES) {
	    if (!classesEnrolledIn[classPrefs[curDesiredClass] - 1])
		return classPrefs[curDesiredClass]; 
	    ++curDesiredClass;
	}
	assert (false);
	return -1;
	*/
    }
    /*
    //returns actual class number, not array index
    public int nextDesiredClass(int curDesiredClass/*also actual class number) {
	assert (curDesiredClass != 7);
	for (int i = curDesiredClass 
		 */

    public String toString(){
        String retVal="";
        
        for(int i =0; i < 7;i++){
                if(classesEnrolledIn[i])
                {
                        int x = i+1;
                        retVal += " " + x;
                }
        }
        return retVal;
    }



}
