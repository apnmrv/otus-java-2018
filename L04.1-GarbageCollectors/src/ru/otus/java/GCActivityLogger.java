package ru.otus.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.TimerTask;

public class GCActivityLogger extends TimerTask {

    private static GCActivityLogger instance;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private DataProcessor proc;

    private GCActivityLogger(DataProcessor proc){
        this.proc = proc;
    };

    public static GCActivityLogger initialize(DataProcessor proc)
    {
        if(null == instance) {
            instance = new GCActivityLogger (proc);
        }
        return instance;
    }

    @Override
    public void run() {
        Queue<GCActivityPerMinute> testData =
                proc.processData(ManagementFactory.getRuntimeMXBean().getUptime());

        while (testData.peek() != null) {
            GCActivityPerMinute obj = testData.remove();
            logger.info("Minute {}, GcType: {}, GcName: {}, GcDuration: {} ms",
                    obj.getMinute(), obj.getGcType(), obj.getGcName(), obj.getGcDuration());
        }

        logger.info("App runtime duration total : {} ms", proc.getAppRuntimePerMinute());
        logger.info("GC activity duration total : {} ms", proc.getGcDurationPerMinute());
    }
}
