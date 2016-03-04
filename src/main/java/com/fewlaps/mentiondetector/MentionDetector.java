package com.fewlaps.mentiondetector;

import java.util.ArrayList;
import java.util.List;

public class MentionDetector {

    private static final String AT_SYMBOL = "@";

    private String text;
    private RemovePunctuationMarks removePunctuationMarks = new RemovePunctuationMarks();

    public MentionDetector(String text) {
        if (text == null) {
            throw new NullPointerException();
        }
        this.text = text;
    }

    public List<Mention> getMentions() {
        List<Mention> mentions = new ArrayList();

        String[] tokens = text.split(" ");
        int start = 0;
        for (String token : tokens) {
            if (token.startsWith(AT_SYMBOL) && token.length() > 2) {
                String usernameWithoutExclamationMarks = removePunctuationMarks.removePunctuationMarks(token);
                mentions.add(new Mention(usernameWithoutExclamationMarks, start, start + usernameWithoutExclamationMarks.length()));
            }
            start += token.length() + 1;
        }

        return mentions;
    }

    public List<Sequence> getSequences() {
        List<Sequence> sequences = new ArrayList();
        List<Mention> mentions = getMentions();

        Sequence sequence = null;
        int i = 0;
        for (Mention mention : mentions) {
            if (sequence == null) {
                sequence = new Sequence();
                sequence.setStart(mention.start());
            }
            if (mentions.size() > i + 1) {
                Mention nextMention = mentions.get(i + 1);
                if (!textBetweenMentionsIsWhitespace(mention, nextMention)) {
                    sequence.setEnd(mention.end());
                    sequences.add(sequence);
                    sequence = null;
                }
            } else {
                sequence.setEnd(mention.end());
                sequences.add(sequence);
                sequence = null;
            }
            i++;
        }
        return sequences;
    }

    private boolean textBetweenMentionsIsWhitespace(Mention firstMention, Mention nextMention) {
        return text.substring(firstMention.end(), nextMention.start()).trim().equals("");
    }
}
