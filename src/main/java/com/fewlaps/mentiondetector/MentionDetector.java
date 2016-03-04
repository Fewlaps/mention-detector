package com.fewlaps.mentiondetector;

import java.util.ArrayList;
import java.util.List;

public class MentionDetector {

    String text;

    public MentionDetector(String text) {
        if (text == null) {
            throw new NullPointerException();
        } else {
            this.text = text;
        }
    }

    public List<Mention> getMentions() {
        List<Mention> mentions = new ArrayList();

        String[] tokens = text.split(" ");
        int start = 0;
        for (String token : tokens) {
            if (token.startsWith("@") && token.length() > 2) {
                mentions.add(new Mention(token, start, start + token.length()));
            }
            start += token.length() + 1;
        }

        return mentions;
    }
}
