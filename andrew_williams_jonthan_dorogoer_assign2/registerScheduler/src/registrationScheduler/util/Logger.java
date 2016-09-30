package registrationScheduler.util;

public class Logger{

    public static enum DebugLevel { NO_OUTPUT, DATA_STRUCTURE, ENTRY_ADDED, RUN_THREAD, CONSTRUCTOR };

    private static DebugLevel debugLevel = DebugLevel.NO_OUTPUT;

    /**
     *
     * Sets debug value for execution
     *
     *@param levelIn value for debug level
     */
    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	case 0: debugLevel = DebugLevel.NO_OUTPUT;      break;
	case 1: debugLevel = DebugLevel.DATA_STRUCTURE; break;
	case 2: debugLevel = DebugLevel.ENTRY_ADDED;    break;
	case 3: debugLevel = DebugLevel.RUN_THREAD;     break;
	case 4: debugLevel = DebugLevel.CONSTRUCTOR;    break;
	}
    }

    /**
     *
     *Sets debug value for execution
     *
     *@param levelIn sets debug level with a DebugLevel
     */
    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    /**
     *Writes a message associated with a debug level
     *
     *@param message message passed to debugger
     *@param levelIn debug level
     */
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    /**
     *@return current debug level
     */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
