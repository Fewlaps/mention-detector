package com.fewlaps.mentiondetector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MentionDetector {

    private static final String AT_SYMBOL = "@";

    private String text;
    private RemovePunctuationMarks removePunctuationMarks = new RemovePunctuationMarks();

    private List<Mention> cache = null;

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
        if (cache == null) {
            cache = parseMentions();
        }
        return cache;
    }

    List<Mention> parseMentions() {
        int atsCount = getAtSymbolsCount();
        if (atsCount == 0) {
            return Collections.emptyList();
        }

        List<Mention> mentions = new ArrayList();

        String[] tokens = text.split(" ");
        int start = 0;
        for (String token : tokens) {
            if (isMention(token)) {
                String usernameWithoutExclamationMarks = removePunctuationMarks.removePunctuationMarks(token);
                mentions.add(new Mention(usernameWithoutExclamationMarks, getMentionStart(token, start)));
            }
            start += token.length() + 1;

            if (mentions.size() == atsCount) {
                break;
            }
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

    private int getAtSymbolsCount() {
        int count = 0;
        int index = 0;
        do {
            index = text.indexOf(AT_SYMBOL, index);
            if (index == -1) {
                return count;
            }
            index += AT_SYMBOL.length();
            count++;
        } while (true);
    }
}
