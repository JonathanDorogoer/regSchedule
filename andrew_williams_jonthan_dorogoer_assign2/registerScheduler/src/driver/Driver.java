import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
	final int NUM_CLASSES = 7;
	
      	FileProcessor fp = new FileProcessor("data.txt");
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
	    
	    //If we were unable to assign five classes, we need to kick someone out of a full class
	    curClass = curStudent.nextDesiredClass(-1);
	    int openClass;
	    for (int i = 0; i < NUM_CLASSES; ++i) {
		if (!classPool.isFull(i)) {
		    openClass = i;
		    break;
		}
	    }
	    for (Student studentToKick : studentList) {
	    }
	    studentList.add(curStudent);
	}
	
    }
}
