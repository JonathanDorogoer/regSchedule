import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DataGen {
    public static void main(String[] args) throws FileNotFoundException {
	ArrayList<Integer> a = new ArrayList<Integer>();

	File f = new File("data.txt");
	PrintWriter p = new PrintWriter(f);
	
	a.add(1);
	a.add(2);
	a.add(3);
	a.add(4);
	a.add(5);
	a.add(6);
	a.add(7);

	for (int i = 1; i <= 80; ++i) {
	    Collections.shuffle(a);
	    p.printf("Student_%d %d %d %d %d %d %d %d\n",
		     i, a.get(0), a.get(1), a.get(2), a.get(3), a.get(4), a.get(5), a.get(6));
	}

	p.close();

    }
}
	
