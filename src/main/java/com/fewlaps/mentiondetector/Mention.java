package com.fewlaps.mentiondetector;

public class Mention {
    private String username;
    private int start;
    private int end;

    public Mention(String username, int start, int end) {
        this.username = username;
        this.start = start;
        this.end = end;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public String username() {
        return username;
    }
}
