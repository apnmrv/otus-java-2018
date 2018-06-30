package ru.otus.java;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class App {

    public static void main(String[] args) {

        TimeCounter tc = TimeCounter.getInnstance();
        // List of GC in use
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();

        // For each of them
        for (GarbageCollectorMXBean gcbean : gcbeans) {

            // Notification emitter
            NotificationEmitter emitter = (NotificationEmitter) gcbean;

            // Notification listener
            NotificationListener listener = new NotificationListener(tc);

            //Add listener to emitter
            emitter.addNotificationListener(listener, null, null);
        }

        // Out of memory error invoker
        try {
            (new OOMInvoker(5)).generateOOM();
        } catch (Exception e) {
            System.out.println("==========================OOM ERROR==================================");
            System.out.println(e.getMessage());
            System.out.println("=====================================================================");
        }
    }
}