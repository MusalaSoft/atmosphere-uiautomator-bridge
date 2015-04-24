package com.musala.atmosphere.uiautomator.logger;

import java.io.File;

import org.apache.log4j.Level;

import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Call {@link #configure()} from the automator's main entry point.
 * 
 * @see <a href="https://code.google.com/p/android-logging-log4j/">https://code.google.com/p/android-logging-log4j/</a>
 * 
 * @author yordan.petrov
 * 
 */
public class Log4JConfigurator {
    private static final String LOGGER_NAME = "org.apache";

    private static final String LOG_FILE = "automator.log";

    private static final String LOG_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + LOG_FILE;

    /**
     * Configures log4j to use an Android appender.
     */
    public static void configure() {
        final LogConfigurator logConfigurator = new LogConfigurator();

        logConfigurator.setFileName(LOG_FILE_PATH);
        logConfigurator.setRootLevel(Level.DEBUG);
        // Set log level of a specific logger
        logConfigurator.setLevel(LOGGER_NAME, Level.ERROR);
        logConfigurator.configure();
    }
}