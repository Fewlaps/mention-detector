package com.fewlaps.mentiondetector;

public class RemovePunctuationMarks {

    public String removePunctuationMarks(String text) {
        return text
                .replaceAll("!", "")
                .replaceAll("\\?", "")
                .replaceAll("¡", "")
                .replaceAll("¿", "")
                ;
    }
}
