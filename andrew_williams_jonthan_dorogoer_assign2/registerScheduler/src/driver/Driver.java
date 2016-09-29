import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
	Logger.setDebugValue(Logger.DebugLevel.DATA_STRUCTURE);

      	FileProcessor fileProcessor = new FileProcessor("data.txt");
	ObjectPool classPool = ObjectPool.getInstance();
	StdoutDisplayInterface results = new Results();

	int numThreads = 3;

	CreateWorkers createWorkers = new CreateWorkers (fileProcessor, results, classPool);
	createWorkers.startWorkers(numThreads);

	Logger.writeMessage(results.toString(), Logger.DebugLevel.DATA_STRUCTURE);
	//results.writeSchedulesToScreen();
	
    }
}
