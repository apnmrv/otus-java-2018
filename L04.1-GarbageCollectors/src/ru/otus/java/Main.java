package ru.otus.java;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Timer;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main (String ... args) {

        DataProcessor proc = DataProcessor
                .initialize(ManagementFactory.getRuntimeMXBean().getUptime());
        GCActivityLogger gcLogger = GCActivityLogger.initialize(proc);

        Timer timer = new Timer();

        // List of GC in use
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();

        // For each of them
        for (GarbageCollectorMXBean gcbean : gcbeans) {

            // Notification emitter
            NotificationEmitter emitter = (NotificationEmitter) gcbean;

            // Notification listener

            NotificationListener listener = new NotificationListener(proc);

            //Add listener to emitter
            emitter.addNotificationListener(listener, null, null);

            logger.info ("GC in use : {}", gcbean.getName());
        }

        // use this to adjust application's runtime duration
        Thread task = new Thread(new MyTask(5, 30000));

        timer.schedule(gcLogger, 60000, 60000);

        try {
            logger.info("App's started at {}", ManagementFactory.getRuntimeMXBean()
                    .getUptime());
            task.run();
        } catch (OutOfMemoryError e) {
            logger.error("OutOfMemoryError reached at {} \n with an exception :\n{}",
                    ManagementFactory.getRuntimeMXBean().getUptime(), e.getMessage());
        }

        logger.info("Exiting application...");

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.stop();

        System.exit(0);
    }
}
