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

    /**
     * Returns the unique instance of Object Pool.
     * If this instance does not yet exist, it is first created then returend
     */
    public static ObjectPool getInstance() {
	Logger.writeMessage ("Instantiating the single instance of ObjectPool", Logger.DebugLevel.CONSTRUCTOR);
	
	//double checked locking to make code more efficient while still being multithread safe
	if (uniqueInstance == null) {
	    synchronized (ObjectPool.class) {
		if (uniqueInstance == null) {
		    uniqueInstance = new ObjectPool();
		}
	    }
	}

	return uniqueInstance;
    }

    /**
     * gives the caller a seat in a specified class
     *
     * @param  classNum the number of the class the seat is being requested from
     * @return true if there was room in the class, false if the class was full
     */ 
    public synchronized boolean borrowObject (int classNum) {
	if (this.isFull(classNum))
	    return false;

	seatsRemaining[classNum] -= 1;
	return true;
    }

    /**
     * returns a seat in a specified class
     *
     * @param classNum the number of the class the seat is being returned to
     */
    public synchronized void returnObject (int classNum) {
	seatsRemaining[classNum] += 1;
    }

    /**
     * gives the number of seats already taken in a specified class
     *
     * @param classNum the number of the class being checked
     * @return the number of seats already taken in the class
     */
    public synchronized int getNumActive (int classNum) {
	return Constants.COURSE_CAPACITY - seatsRemaining[classNum];
    }

    /**
     * gives the number of still available seats in a specified class
     *
     * @param classNum the number of the class being checked
     * @return the number of remaining seats in the class
     */
    public synchronized int getNumIdle (int classNum) {
	return seatsRemaining[classNum];
    }

    /**
     * checks if a specified class is full
     *
     * @param classNum the number of the class being checked
     * @return true if the class is full, false if the class has seats remaining
     */
    public synchronized boolean isFull(int classNum) {
	return (seatsRemaining[classNum] == 0);
    }

    /**
     * checks how many classes still have seats remaining
     * @return the number of classes that are not currently full
     */
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
