package ru.otus.java;

import java.util.LinkedList;
import java.util.Queue;

public class DataProcessor {

    private static DataProcessor instance;
    private Queue<GCActivityRawData> dataSet = new LinkedList<>();
    private Queue<GCActivityPerMinute> resultSet = new LinkedList<>();

    private long timerOffset;
    private long gcDurationPerMinute = 0;

    private DataProcessor(long timerOffset) {
        this.timerOffset = timerOffset;
    }

    public static DataProcessor initialize(long startTime)
    {
        if(null == instance) {
            instance = new DataProcessor(startTime);
        }
        return instance;
    }

    public synchronized void addData (GCActivityRawData data)
    {
        dataSet.add(data);
    }

    public Queue<GCActivityPerMinute> processData(long tillTime)
    {
        int currentMinute = getMinute(tillTime - timerOffset);

        if(dataSet.peek() == null){
            resultSet.add(new GCActivityPerMinute(currentMinute,
                    null, null, 0));
        }
        while (dataSet.peek() != null) {
            GCActivityRawData gc = dataSet.remove();
            long gcDuration = gc.getDuration();
            resultSet.add(new GCActivityPerMinute(currentMinute, gc.getType(), gc.getName(),
                    gcDuration));
            gcDurationPerMinute += gcDuration;
        }

        return resultSet;
    }

    private int getMinute (long ms) {
        return (int)(ms/60000);
    }

    public long getGcDurationPerMinute() {
        long result = gcDurationPerMinute;
        gcDurationPerMinute = 0;
        return result;
    }

    public long getAppRuntimePerMinute(){
        return 60000 - gcDurationPerMinute;
    }
}
