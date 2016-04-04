package com.fewlaps.mentiondetector;

import com.fewlaps.quitnowemailsuggester.EmailValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MentionDetector {

    private static final String AT_SYMBOL = "@";
    private static final String WHITESPACE = " ";

    private final String text;
    private final RemovePunctuationMarks removePunctuationMarks = new RemovePunctuationMarks();
    private final EmailValidator emailValidator = new EmailValidator();

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
        for (int atPosition : atPositions) {
            if (!isEmail(getWordAtPosition(atPosition))) {
                String word = getWordStartingAtPosition(atPosition);
                if (isMention(word)) {
                    String usernameWithoutExclamationMarks = removePunctuationMarks.removePunctuationMarks(word);
                    mentions.add(new Mention(usernameWithoutExclamationMarks, getMentionStart(word, text.indexOf(word))));
                }
            }
        }

        return mentions;
    }

    private boolean isEmail(String word) {
        return emailValidator.isValidEmail(word);
    }

    private String getWordAtPosition(int position) {
        String beforePosition = getWordEndingAtPosition(position);
        String afterPosition = getWordStartingAtPosition(position);
        String word = beforePosition + afterPosition;
        return word;
    }

    private String getWordStartingAtPosition(Integer position) {
        String substring = getWordWithoutTheAtSymbol(position);
        StringTokenizer st = new StringTokenizer(substring, WHITESPACE + AT_SYMBOL);
        return AT_SYMBOL + st.nextToken();
    }

    private String getWordEndingAtPosition(int position) {
        String startingText = text.substring(0, position);
        int lastIndex = startingText.lastIndexOf(" ");
        if (lastIndex == -1) {
            return "";
        } else {
            return startingText.substring(lastIndex, position).trim();
        }
    }

    private String getWordWithoutTheAtSymbol(Integer position) {
        return text.substring(position + 1);
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
