package com.fewlaps.mentiondetector.performance;

import com.fewlaps.mentiondetector.MentionDetector;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PerformanceTest {

    public static final int REPETITIONS = 100000;

    public static final String LOREM_IPSUM_WITHOUT_MENTIONS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final String LOREM_IPSUM_WITH_3_MENTIONS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, @mention1 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. @mention2 Excepteur sint occaecat cupidatat non proident, @mention3 sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final String LOREM_IPSUM_WITH_1_MENTION_AT_START = "@banana Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Test
    @Ignore
    public void shouldLastLessThanOneMillisecond_whenCallingGetMentions_withZeroMentions() throws InterruptedException {
       long millisTaken = getTimeToGetMentions(LOREM_IPSUM_WITHOUT_MENTIONS, 1);
        assertThat(millisTaken).isLessThan(1);
    }

    @Test
    @Ignore
    public void shouldLastLessThanOneMillisecond_whenCallingGetMentions_withOneMention() throws InterruptedException {
        long millisTaken = getTimeToGetMentions(LOREM_IPSUM_WITH_1_MENTION_AT_START, 1);
        assertThat(millisTaken).isLessThan(1);
    }

    @Test
    @Ignore
    public void shouldLastLessThanTwoMillis_whenCallingGetMentions_withThreeMentions() throws InterruptedException {
        long millisTaken = getTimeToGetMentions(LOREM_IPSUM_WITH_3_MENTIONS, 1);
        assertThat(millisTaken).isLessThan(2);
    }

    @Test
    public void runGetMentionsOnce() throws InterruptedException {
        System.out.println("getMentions() with 0 mentions: " + getTimeToGetMentions(LOREM_IPSUM_WITHOUT_MENTIONS, 1) + "ms");
        System.out.println("getMentions() with 3 mention at the middle: " + getTimeToGetMentions(LOREM_IPSUM_WITH_3_MENTIONS, 1) + "ms");
        System.out.println("getMentions() with 1 mention at the start: " + getTimeToGetMentions(LOREM_IPSUM_WITH_1_MENTION_AT_START, 1) + "ms");
    }

    @Test
    public void runGetMentions100000times() throws InterruptedException {
        System.out.println(REPETITIONS + "getMentions() with 0 mentions: " + getTimeToGetMentions(LOREM_IPSUM_WITHOUT_MENTIONS, REPETITIONS) + "ms");
        System.out.println(REPETITIONS + "getMentions() with 3 mention at the middle: " + getTimeToGetMentions(LOREM_IPSUM_WITH_3_MENTIONS, REPETITIONS) + "ms");
        System.out.println(REPETITIONS + "getMentions() with 1 mention at the start: " + getTimeToGetMentions(LOREM_IPSUM_WITH_1_MENTION_AT_START, REPETITIONS) + "ms");
    }

    @Test
    public void runGetSequences100000times() throws InterruptedException {
        System.out.println(REPETITIONS + "getSequences() with 0 mentions: " + getTimeToGetSequences(LOREM_IPSUM_WITHOUT_MENTIONS, REPETITIONS) + "ms");
        System.out.println(REPETITIONS + "getSequences() with 3 mention at the middle: " + getTimeToGetSequences(LOREM_IPSUM_WITH_3_MENTIONS, REPETITIONS) + "ms");
        System.out.println(REPETITIONS + "getSequences() with 1 mention at the start: " + getTimeToGetSequences(LOREM_IPSUM_WITH_1_MENTION_AT_START, REPETITIONS) + "ms");
    }

    private long getTimeToGetMentions(String text, int repetitions) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < repetitions; i++) {
            MentionDetector detector = new MentionDetector(text);
            detector.getMentions();
        }
        return System.currentTimeMillis() - startTime;
    }

    private long getTimeToGetSequences(String text, int repetitions) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < repetitions; i++) {
            MentionDetector detector = new MentionDetector(text);
            detector.getSequences();
        }
        return System.currentTimeMillis() - startTime;
    }
}
