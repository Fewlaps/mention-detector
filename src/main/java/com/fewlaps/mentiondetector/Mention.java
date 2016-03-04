package com.fewlaps.mentiondetector;

public class Mention {

    private static final String AT_SYMBOL = "@";

    private String username;
    private int start;
    private int end;

    public Mention(String username, int start, int end) {
        if (!username.contains(AT_SYMBOL)) {
            throw new IllegalArgumentException("This username doesn't start with @. Instead of passing " + username + ", pass @" + username);
        }
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

    public String usernameWithoutAtSymbol() {
        return username.replaceAll("@", "");
    }
}
