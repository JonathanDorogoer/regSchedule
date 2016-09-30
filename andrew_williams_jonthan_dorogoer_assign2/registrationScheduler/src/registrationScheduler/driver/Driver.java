package registrationScheduler.driver;

import java.util.ArrayList;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.results.StdoutDisplayInterface;
import registrationScheduler.results.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.objectPool.ObjectPoolInterface;

public class Driver {
    
    /**
     *Main function in drive class, intializes important components and starts the worker
     *threads with corresponding components
     *Also intializes and passes Logger
     *Finally, prints average preferrence score
     *
     */
    public static void main(String[] args) {
	if (args.length != 4) {
	    System.out.printf("Please run with exactly four arguments, as follows:\n./java -jar registrationScheduler.jar <inputfilename> <outputfilename> <number of threads> <debug value>\n");
	    System.exit(0);
	}

	int numThreads = Integer.parseInt(args[2]);
	if (numThreads < 1 || numThreads > 3) {
	    System.out.printf ("Error: the number of threads must be between one and three\n");
	    System.exit(0);
	}

	int debugValue = Integer.parseInt(args[3]);
	if (debugValue < 0 || debugValue > 4) {
	    System.out.printf ("Error: debug value must be between zero and four\n");
	    System.exit(0);
	}
	Logger.setDebugValue(debugValue);
	
      	FileProcessor fileProcessor = new FileProcessor(args[0]);
	ObjectPoolInterface classPoolInterface = ObjectPool.getInstance();
	StdoutDisplayInterface resultsInterface = new Results();
	Results results = (Results) resultsInterface;

	CreateWorkers createWorkers = new CreateWorkers (fileProcessor, resultsInterface, classPoolInterface);
	createWorkers.startWorkers(numThreads);

	results.writeSchedulesToScreen();
	results.writeSchedulesToFile(args[1]);
	System.out.printf("The average preference value is %.1f\n", results.getAveragePrefScore());
    }
}
