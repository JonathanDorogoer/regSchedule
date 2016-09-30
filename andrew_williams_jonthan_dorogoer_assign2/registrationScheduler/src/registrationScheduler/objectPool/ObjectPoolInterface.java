package registrationScheduler.objectPool;

public interface ObjectPoolInterface {
    public boolean borrowObject (int classNum);
    public void returnObject (int classNum);
    public int getNumActive (int classNum);
    public int getNumIdle (int classNum);
}
