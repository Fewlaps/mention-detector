package com.fewlaps.mentiondetector;

public class Mention {

    private static final String AT_SYMBOL = "@";

    private String username;
    private int start;

    public Mention(String username, int start) {
        if (!username.contains(AT_SYMBOL)) {
            throw new IllegalArgumentException("This username doesn't start with @. Instead of passing " + username + ", pass @" + username);
        }
        this.username = username;
        this.start = start;
    }

    public int start() {
        return start;
    }

    public int end() {
        return start + username.length();
    }

    public String username() {
        return username;
    }

    public String usernameWithoutAtSymbol() {
        return username.replaceAll("@", "");
    }
}
