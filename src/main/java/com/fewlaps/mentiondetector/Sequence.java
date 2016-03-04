package com.fewlaps.mentiondetector;

public class Sequence {
    private int start;
    private int end;

    void setStart(int start) {
        this.start = start;
    }

    void setEnd(int end) {
        this.end = end;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }
}
