import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
	final int NUM_CLASSES = 7;
	
      	FileProcessor fp = new FileProcessor("samedata.txt");
	ArrayList<Student> studentList = new ArrayList<Student>();
	ObjectPool classPool = ObjectPool.getInstance();

	int s = 0;

	while (fp.hasNextLine()) {
	    ++s;
	    Student curStudent = new Student(fp.getNextLine());
		
	    //try to enroll Student in five classes
	    int curPref = -1;
	    int curClass;
	    while (curStudent.numClasses() < 5) {
		curClass = curStudent.nextDesiredClass(curPref);
		if (curClass == -1) break;
		if (classPool.getSeat(curClass)) {
		    curStudent.enrollInClass(curClass);
		    System.out.printf("Enrolling student_%d in class %d.\n", s, curClass);
		}
		curPref = curStudent.getPrefByClass(curClass);
	    }
	    
	    
	    while (curStudent.numClasses() < 5) {
		//If we were unable to assign five classes, we need to kick someone out of a full class
		curClass = curStudent.nextDesiredClass(-1);
		int openClass = -1;
		for (int i = 0; i < NUM_CLASSES; ++i) {
		    if (!classPool.isFull(i)) {
			openClass = i;
			break;
		    }
		}
		for (Student studentToKick : studentList) {
		    if (studentToKick.isEnrolled(curClass) && studentToKick.isNotEnrolled(openClass)) {
			System.out.printf("Kicking student out class %d and enrolling him in class %d\n", curClass, openClass);
			studentToKick.dropClass(curClass);
			curStudent.enrollInClass(curClass);
			classPool.getSeat(openClass);
			studentToKick.enrollInClass(openClass);
			break;
		    }
		}
	    }
	    studentList.add(curStudent);
	}
    
	int i = 0;
	for (Student S : studentList) {
	    System.out.printf("Student_%d %s\n", ++i, S.toString());
	}

    }
}
