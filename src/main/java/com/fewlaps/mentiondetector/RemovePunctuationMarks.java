package com.fewlaps.mentiondetector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RemovePunctuationMarks {

    private static final String EMPTY = "";
    private static final String[] PUNCTUATION_MARKS = {
            "!", "\\?", "¡", "¿", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]"
    };

    private static List<Pattern> cache;

    public String removePunctuationMarks(String text) {
        for (Pattern pattern : getPatterns()) {
            text = pattern.matcher(text).replaceAll(EMPTY);
        }
        return text;
    }

    private List<Pattern> getPatterns() {
        if (cache == null) {
            cache = new ArrayList(PUNCTUATION_MARKS.length);
            for (String mark : PUNCTUATION_MARKS) {
                cache.add(Pattern.compile(mark));
            }
        }
        return cache;
    }
}
