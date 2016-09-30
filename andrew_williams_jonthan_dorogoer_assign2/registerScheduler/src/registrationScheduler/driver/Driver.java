package registrationScheduler.driver;
import java.util.ArrayList;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.result.StdoutDisplayInterface;
import registrationScheduler.result.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.objectPool.ObjectPool;

public class Driver {
    
    /**
     *Main function in drive class, intializes important components and starts the worker
     *threads with corresponding components
     *Also intializes and passes Logger
     *Finally, prints average preferrence score
     *
     */
    public static void main(String[] args) {
	Logger.setDebugValue(Logger.DebugLevel.DATA_STRUCTURE);

      	FileProcessor fileProcessor = new FileProcessor("src/registrationScheduler/driver/data.txt");
	ObjectPool classPool = ObjectPool.getInstance();
	StdoutDisplayInterface resultsInterface = new Results();
	Results results = (Results) resultsInterface;

	int numThreads = 3;

	CreateWorkers createWorkers = new CreateWorkers (fileProcessor, resultsInterface, classPool);
	createWorkers.startWorkers(numThreads);

	Logger.writeMessage(results.toString(), Logger.DebugLevel.DATA_STRUCTURE);
	System.out.printf("The average preference value is %.1f\n", results.getAveragePrefScore());
	//results.writeSchedulesToScreen();
	
    }
}
