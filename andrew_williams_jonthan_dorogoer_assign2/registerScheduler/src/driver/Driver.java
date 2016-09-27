import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
		FileProcessor fp = new FileProcessor("data.txt");
	ArrayList<Student> studentList = new ArrayList<Student>();
	ObjectPool classPool = ObjectPool.getInstance();

	while (fp.hasNextLine()) {
	    Student curStudent = new Student(fp.getNextLine());
	    
	    int curClassIndex = 0;

	    while (curStudent.numClasses < 5) {
		while (curStudent.numClasses < 5 && curClassIndex < 7) {
		    curClassIndex = curStudent.nextDesiredClass(curClassIndex);
		    

		}


		//IF WE HAVE TO KICK SOMEONE OUT
		
	    }


	}
	/*

	Student S = new Student ("Student_x 1 5 2 3 7 4 6");
	
	S.enrollInClass(3);
	S.enrollInClass(6);
	for (int i = 0; i < 7; ++i) {
	    System.out.printf("%d ", S.nextDesiredClass(i));
	}
	System.out.printf("\n");
	*/
    }
}