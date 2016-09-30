package registrationScheduler.threadMgmt;
import java.util.ArrayList;
import registrationScheduler.util.Logger;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.result.StdoutDisplayInterface;
import registrationScheduler.util.FileProcessor;

public class CreateWorkers {
    private FileProcessor          fileProcessor;
    private StdoutDisplayInterface results;
    private ObjectPool             classPool;
/**
 *Creates a new worker.
 *
 *@param fp passes a fp to a worker
 *@param r passes a StdoutInterface to a worker
 *@param cp passes a objectPool to a worker
 */
    public CreateWorkers (FileProcessor fp, StdoutDisplayInterface r, ObjectPool cp) {
	Logger.writeMessage ("Instantiating a CreateWorkers", Logger.DebugLevel.CONSTRUCTOR);

	fileProcessor = fp;
	results = r;
	classPool = cp;
    }
/**
 * Starts a number of workers depending on number of threads passed.
 *@param numThreads passes number of threads needed for execution
 */
    public void startWorkers (int numThreads) {
	ArrayList<Thread> threadList = new ArrayList<Thread>();

	Runnable threadJob = new WorkerThread(fileProcessor, results, classPool);
	for (int i = 0; i < numThreads; ++i)
	    threadList.add(new Thread(threadJob));

	for (Thread thread : threadList)
	    thread.start();

	for (Thread thread : threadList) {
	    try {
		thread.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
		System.exit(0);
	    }
    	}
    }

}
