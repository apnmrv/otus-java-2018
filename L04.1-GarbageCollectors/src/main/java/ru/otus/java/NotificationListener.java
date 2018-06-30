package ru.otus.java;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.openmbean.CompositeData;
import java.lang.management.MemoryUsage;
import java.util.Map;

public class NotificationListener implements javax.management.NotificationListener {

    private TimeCounter tc = null;

    public NotificationListener(TimeCounter tc) {
        this.tc = tc;
    }

    public void handleNotification(Notification notification, Object handback) {

        if (notification.getType().equals(GarbageCollectionNotificationInfo
                .GARBAGE_COLLECTION_NOTIFICATION)) {

            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo
                            .from((CompositeData) notification.getUserData());

            long duration = info.getGcInfo().getDuration();
            String gctype = info.getGcAction();

            if ("end of minor GC".equals(gctype)) {
                gctype = "Young Generation GC";
                tc.addMinorGcDuration(duration);
            } else if ("end of major GC".equals(gctype)) {
                gctype = "Old Generation GC";
                tc.addMajorGcDuration(duration);
            }

            tc.addTotalGcDuration(duration);

            System.out.println("=====================================================================");
            System.out.println(gctype);
            System.out.println(info.getGcAction());
            System.out.println(info.getGcName() + " number " + info.getGcInfo().getId());
            System.out.println("caused by " + info.getGcCause());

            System.out.println("++++++++++++++++MEMORY++++++++++++++++++++++");
            //Get the information about each memory space, and pretty print it
            Map<String, MemoryUsage> mem = info.getGcInfo().getMemoryUsageAfterGc();
            for (Map.Entry<String, MemoryUsage> entry : mem.entrySet()) {
                String name = entry.getKey();
                MemoryUsage memdetail = entry.getValue();
                long memCommitted = memdetail.getCommitted();
                long memMax = memdetail.getMax();
                long memUsed = memdetail.getUsed();
                System.out.print(name + (memCommitted==memMax?"(no more place)":"(there's still some place)"));
                System.out.println(((memUsed/1048576)+1)+"MB)");
            }

            System.out.println("++++++++++++++++TIME++++++++++++++++++++++");
            System.out.println("started at " + info.getGcInfo().getStartTime());
            System.out.println("ended at " + info.getGcInfo().getEndTime());
            System.out.println("lasted " + duration + " ms");

            System.out.println("++++++++++++++++TIME SUMMARY+++++++++++++++");
            System.out.println("GC time so far in total: " + tc.getTotalGcDuration() + " ms");
            System.out.println("Young generation GC time so far in total: " + tc.getMinorGcDuration() + " ms");
            System.out.println("Old generation GC time so far in total: " + tc.getMajorGcDuration() + " ms");
            System.out.println("=====================================================================");
        }
    }
}
