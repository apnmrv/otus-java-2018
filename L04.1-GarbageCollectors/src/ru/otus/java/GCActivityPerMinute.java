package ru.otus.java;

public class GCActivityPerMinute {

    private int minute;
    private String gcType;
    private String gcName;
    private long gcDuration;

    public GCActivityPerMinute(int minute, String gcType, String gcName,
                               long gcDuration) {
        this.minute = minute;
        this.gcDuration = gcDuration;
        this.gcType = gcType;
        this.gcName = gcName;
    }

    public int getMinute() {
        return minute;
    }

    public long getGcDuration() {
        return gcDuration;
    }

    public String getGcType() {
        return gcType;
    }

    public String getGcName() {
        return gcName;
    }

}
