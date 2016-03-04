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

    public boolean hasMentions() {
        return !getMentions().isEmpty();
    }

    public List<Mention> getMentions() {
        List<Mention> mentions = new ArrayList();

        String[] tokens = text.split(" ");
        int start = 0;
        for (String token : tokens) {
            if (isMention(token)) {
                String usernameWithoutExclamationMarks = removePunctuationMarks.removePunctuationMarks(token);
                mentions.add(new Mention(usernameWithoutExclamationMarks, getMentionStart(token, start)));
            }
            start += token.length() + 1;
        }

        return mentions;
    }

    private int getMentionStart(String token, int tokenIndex) {
        return tokenIndex + token.indexOf(AT_SYMBOL);
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

    private boolean isMention(String token) {
        String tokenWithoutPunctuationMarks = removePunctuationMarks.removePunctuationMarks(token);
        return tokenWithoutPunctuationMarks.startsWith(AT_SYMBOL) && tokenWithoutPunctuationMarks.length() > 2;
    }

    private boolean textBetweenMentionsIsWhitespace(Mention firstMention, Mention nextMention) {
        return text.substring(firstMention.end(), nextMention.start()).trim().equals("");
    }
}
