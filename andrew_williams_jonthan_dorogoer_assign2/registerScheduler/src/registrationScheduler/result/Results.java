package registrationScheduler.result;
import java.util.ArrayList;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Constants;

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

    public void writeSchedulesToFile() {
	System.out.printf("writeSchedulesToFile\n");
    }

    public synchronized void addStudent (Student student) {
	Logger.writeMessage ("Adding student " + student.getName() + " to Results Class", Logger.DebugLevel.ENTRY_ADDED);

	students.add(student);
    }

/**
 *
 *@param fullClass a full class
 *@param openClass a open class
 *@return finds and returns a student who is in fullClass but not in openclass
 */
    public synchronized Student findStudentToKick (int fullClass, int openClass) {
	for (Student s : students) {
	    if (s.isEnrolled(fullClass) && s.isNotEnrolled(openClass))
		return s;
		}
	Student s = new Student("crashit");
	return s;
    }


/**
 *
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
 *
 *gets a average preferrence score from all students in the student list
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

    
