package registrationScheduler.result;
import java.util.ArrayList;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Constants;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {
    ArrayList<Student> students = new ArrayList<Student>();

    public Results() {
	Logger.writeMessage ("Instantiating a Results", Logger.DebugLevel.CONSTRUCTOR);
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	
	for (Student s : students) {
	    sb.append(s.toString());
	    sb.append("\n");
	}

	return sb.toString();
    }

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

    public void writeSchedulesToFile() {
	System.out.printf("writeSchedulesToFile\n");
    }

    public synchronized void addStudent (Student student) {
	Logger.writeMessage ("Adding student " + student.getName() + " to Results Class", Logger.DebugLevel.ENTRY_ADDED);

	students.add(student);
    }

    //finds and returns a student who is in fullClass but not in openclass
    public synchronized Student findStudentToKick (int fullClass, int openClass) {
	for (Student s : students) {
	    if (s.isEnrolled(fullClass) && s.isNotEnrolled(openClass))
		return s;
	}
	Student s = new Student("crashit");
	return s;
    }
    
    public synchronized void enrollStudentInClass (Student student, int classNum) {
	student.enrollInClass (classNum);
    }

    public synchronized void removeStudentFromClass (Student student, int classNum) {
	student.dropClass (classNum);
    }

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

    
