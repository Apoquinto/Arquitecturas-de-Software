package model;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.File;

public class Log {
    private static Logger logger;
    private static final String logFilename = "log.txt";

    private static void initializeLogger() {
        try {
            System.setProperty("java.util.logging.SimpleFormatter.format",
              "[%2$s] %1$tc%n");
            logger = Logger.getLogger(Log.class.getName());
            FileHandler fileHandler = new FileHandler(logFilename,true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.out.println("Log module is not working properly");
            logger = Logger.getLogger("aux");
        }
    }

    private static void rebuildLogFile() {
        try {
            new File(logFilename).delete();
        } catch (Exception e) {
            new File(logFilename);
        }
    }

    public static Logger getLogger() {
        if (logger == null) {
            rebuildLogFile();
            initializeLogger();  
        }
        logger.info("");
        return logger;
    }
}
