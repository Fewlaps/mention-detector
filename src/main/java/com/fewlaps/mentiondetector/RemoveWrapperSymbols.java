package com.fewlaps.mentiondetector;

import java.util.regex.Pattern;

public class RemoveWrapperSymbols {

    private static final Pattern startingKey = Pattern.compile(Pattern.quote("{"));
    private static final Pattern endingKey = Pattern.compile(Pattern.quote("}"));
    private static final Pattern startingParenthesis = Pattern.compile(Pattern.quote("("));
    private static final Pattern endingParenthesis = Pattern.compile(Pattern.quote(")"));
    private static final Pattern startingClause = Pattern.compile(Pattern.quote("["));
    private static final Pattern endingClause = Pattern.compile(Pattern.quote("]"));

    public String removeSymbols(String text) {
        text = clean(text, startingKey);
        text = clean(text, endingKey);
        text = clean(text, startingParenthesis);
        text = clean(text, endingParenthesis);
        text = clean(text, startingClause);
        text = clean(text, endingClause);
        return text;
    }

    private static String clean(String text, Pattern pattern) {
        return pattern.matcher(text).replaceAll("");
    }
}
