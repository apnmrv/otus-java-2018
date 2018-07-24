package ru.otus.java;

public class GCActivityRawData {

    private String type;
    private String name;
    private long startTime;
    private long finishTime;
    private long duration;

    public GCActivityRawData(String type, String name,
                             long startedAt, long finishedAt, long duration) {
        this.type = type;
        this.name = name;
        this.startTime = startedAt;
        this.finishTime = finishedAt;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public long getDuration() {
        return duration;
    }
}
