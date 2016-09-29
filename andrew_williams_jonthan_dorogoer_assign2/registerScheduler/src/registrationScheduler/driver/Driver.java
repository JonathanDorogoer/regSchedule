package registrationScheduler.driver;
import java.util.ArrayList;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;

import registrationScheduler.result.StdoutDisplayInterface;

import registrationScheduler.result.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.objectPool.ObjectPool;
public class Driver {
    public static void main(String[] args) {
	Logger.setDebugValue(Logger.DebugLevel.DATA_STRUCTURE);

      	FileProcessor fileProcessor = new FileProcessor("src/registrationScheduler/driver/data.txt");
	ObjectPool classPool = ObjectPool.getInstance();
	StdoutDisplayInterface results = new Results();

	int numThreads = 3;

	CreateWorkers createWorkers = new CreateWorkers (fileProcessor, results, classPool);
	createWorkers.startWorkers(numThreads);

	Logger.writeMessage(results.toString(), Logger.DebugLevel.DATA_STRUCTURE);
	//results.writeSchedulesToScreen();
	
    }
}
