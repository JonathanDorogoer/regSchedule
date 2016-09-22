public class ObjectPool {
    
    private static ObjectPool uniqueInstance;

    private final int MAX_CLASS_SIZE = 60;
    private final int NUM_CLASSES    = 7;

    private int[] seatsRemaining = {MAX_CLASS_SIZE, MAX_CLASS_SIZE, MAX_CLASS_SIZE, MAX_CLASS_SIZE, MAX_CLASS_SIZE, MAX_CLASS_SIZE, MAX_CLASS_SIZE};

    private ObjectPool() {}

    public static ObjectPool getInstance() {
	if (uniqueInstance == null)
	    uniqueInstance = new ObjectPool();

	return uniqueInstance;
    }

    boolean getSeat(int classNum) {
	assert (classNum < NUM_CLASSES);

	if (this.isFull(classNum))
	    return false;

	seatsRemaining[classNum] -= 1;
	return true;
    }

    void giveSeat (int classNum) {
	assert (classNum < NUM_CLASSES);
	seatsRemaining[classNum] += 1;
	assert (seatsRemaining[classNum] <= MAX_CLASS_SIZE);
    }

    boolean isFull(int classNum) {
	assert (classNum < NUM_CLASSES);

	return (seatsRemaining[classNum] == 0);
    }
}
