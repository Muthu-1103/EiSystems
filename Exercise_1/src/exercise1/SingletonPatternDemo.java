package exercise1;

class Logger {
    private static Logger instance;
    
    private Logger() {
        // Private constructor to prevent instantiation
    }
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}

// Main class
public class SingletonPatternDemo {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        logger1.log("Logger instance 1");
        logger2.log("Logger instance 2");
        
        System.out.println("Are both instances same? " + (logger1 == logger2));
    }
}
