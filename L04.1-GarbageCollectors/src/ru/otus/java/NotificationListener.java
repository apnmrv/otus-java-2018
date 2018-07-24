package ru.otus.java;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.Notification;
import javax.management.openmbean.CompositeData;

public class NotificationListener
        implements javax.management.NotificationListener {

    private DataProcessor processor = null;
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    public NotificationListener(DataProcessor processor) {
        this.processor = processor;
    }

    public void handleNotification(Notification notification, Object handback) {

        if (notification.getType().equals(GarbageCollectionNotificationInfo
                .GARBAGE_COLLECTION_NOTIFICATION)) {

            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo
                            .from((CompositeData) notification.getUserData());

            String gctype = info.getGcAction();

            if ("end of minor GC".equals(gctype)) {
                gctype = "Young Generation GC";
            } else if ("end of major GC".equals(gctype)) {
                gctype = "Old Generation GC";
            }

            GCActivityRawData gc = new GCActivityRawData(gctype,
                    info.getGcName(), info.getGcInfo().getStartTime(),
                    info.getGcInfo().getEndTime(), info.getGcInfo().getDuration());
            processor.addData(gc);
        }
    }
}