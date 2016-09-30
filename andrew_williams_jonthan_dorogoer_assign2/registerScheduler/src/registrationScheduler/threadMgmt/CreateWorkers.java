package registrationScheduler.threadMgmt;

import java.util.ArrayList;

import registrationScheduler.util.Logger;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.objectPool.ObjectPoolInterface;
import registrationScheduler.results.StdoutDisplayInterface;
import registrationScheduler.util.FileProcessor;

public class CreateWorkers {
    private FileProcessor          fileProcessor;
    private StdoutDisplayInterface results;
    private ObjectPoolInterface    classPoolInterface;
    
    /**
     *@param fileProcessorIn FileProcessor to pass to worker threads
     *@param resultsin StdoutDisplayInterface to pass to worker threads
     *@param classPoolInterfaceIn ObjectPoolInterface to pass to worker threads
     */
    public CreateWorkers (FileProcessor fileProcessorIn, StdoutDisplayInterface resultsIn, ObjectPoolInterface classPoolInterfaceIn) {
	Logger.writeMessage ("Instantiating a CreateWorkers", Logger.DebugLevel.CONSTRUCTOR);

	fileProcessor = fileProcessorIn;
	results = resultsIn;
	classPoolInterface = classPoolInterfaceIn;
    }

    /**
     *Starts a number of workers depending on number of threads passed
     *@param numThreads number of threads to be started
     */
    public void startWorkers (int numThreads) {
	ArrayList<Thread> threadList = new ArrayList<Thread>();
	Runnable threadJob = new WorkerThread(fileProcessor, results, classPoolInterface);

	for (int i = 0; i < numThreads; ++i)
	    threadList.add(new Thread(threadJob));

	for (Thread thread : threadList)
	    thread.start();

	for (Thread thread : threadList) {
	    try {
		thread.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
		System.exit(1);
	    }
    	}
    }
}
