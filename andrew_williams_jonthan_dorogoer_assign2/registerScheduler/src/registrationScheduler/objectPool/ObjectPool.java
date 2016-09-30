package registrationScheduler.objectPool;

import registrationScheduler.util.Constants;
import registrationScheduler.util.Logger;

public class ObjectPool implements ObjectPoolInterface {
    
    private volatile static ObjectPool uniqueInstance;

    private int[] seatsRemaining = {Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY,
				    Constants.COURSE_CAPACITY};

    private ObjectPool() {}

    public static ObjectPool getInstance() {
	Logger.writeMessage ("Instantiating the single instance of ObjectPool", Logger.DebugLevel.CONSTRUCTOR);

	if (uniqueInstance == null) {
	    synchronized (ObjectPool.class) {
		if (uniqueInstance == null) {
		    uniqueInstance = new ObjectPool();
		}
	    }
	}

	return uniqueInstance;
    }

    public synchronized boolean borrowObject (int classNum) {
	if (this.isFull(classNum))
	    return false;

	seatsRemaining[classNum] -= 1;
	return true;
    }

    public synchronized void returnObject (int classNum) {
	seatsRemaining[classNum] += 1;
    }

    public synchronized int getNumActive (int classNum) {
	return Constants.COURSE_CAPACITY - seatsRemaining[classNum];
    }

    public synchronized int getNumIdle (int classNum) {
	return seatsRemaining[classNum];
    }

    public synchronized boolean isFull(int classNum) {
	return (seatsRemaining[classNum] == 0);
    }

    public synchronized int numClassesOpen() {
	int retVal = 0;

	for (int i = 0; i < Constants.NUM_COURSES; ++i) {
	    if (seatsRemaining[i] > 0) {
		++retVal;
	    }
	}
	return retVal;
    }
}
