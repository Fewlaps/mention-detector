package com.fewlaps.mentiondetector;

public class RemovePunctuationMarks {

    private static final String EMPTY = "";
    private static final String[] PUNCTUATION_MARKS = {
            "!", "\\?", "¡", "¿"
    };

    public String removePunctuationMarks(String text) {
        for (String mark : PUNCTUATION_MARKS) {
            text = text.replaceAll(mark, EMPTY);
        }
        return text;
    }
}
