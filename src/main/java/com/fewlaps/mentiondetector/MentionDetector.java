package com.fewlaps.mentiondetector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MentionDetector {

    private static final String AT_SYMBOL = "@";

    private final String text;
    private final RemovePunctuationMarks removePunctuationMarks = new RemovePunctuationMarks();

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
        List<Integer> atPositions = getAtSymbolsCount();
        if (atPositions.isEmpty()) {
            return Collections.emptyList();
        }

        List<Mention> mentions = new ArrayList();
        for (Integer atPosition : atPositions) {
            String word = getWordAtPosition(atPosition);
            if (isMention(word)) {
                String usernameWithoutExclamationMarks = removePunctuationMarks.removePunctuationMarks(word);
                mentions.add(new Mention(usernameWithoutExclamationMarks, getMentionStart(word, text.indexOf(word))));
            }
        }

        return mentions;
    }

    private String getWordAtPosition(Integer position) {
        String substring = text.substring(position);
        StringTokenizer st = new StringTokenizer(substring, " ");
        return st.nextToken();
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

    private List<Integer> getAtSymbolsCount() {
        List<Integer> positions = new ArrayList();
        int index = 0;
        do {
            index = text.indexOf(AT_SYMBOL, index);
            if (index == -1) {
                return positions;
            } else {
                positions.add(index);
            }
            index += AT_SYMBOL.length();
        } while (true);
    }
}
