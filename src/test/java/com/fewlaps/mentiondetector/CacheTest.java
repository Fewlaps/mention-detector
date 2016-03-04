package com.fewlaps.mentiondetector;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class CacheTest {

    @Test
    public void shouldCallParseTextOnce_whenCallingGetMentionsTwice() throws InterruptedException {
        MentionDetector mentionDetector = spy(new MentionDetector("a text"));

        mentionDetector.getMentions();
        mentionDetector.getMentions();

        verify(mentionDetector, times(1)).parseMentions();
    }

    @Test
    public void shouldCallParseTextOnce_whenCallingHasMentionsTwice() throws InterruptedException {
        MentionDetector mentionDetector = spy(new MentionDetector("a text"));

        mentionDetector.hasMentions();
        mentionDetector.hasMentions();

        verify(mentionDetector, times(1)).parseMentions();
    }

    @Test
    public void shouldCallParseTextOnce_whenCallingHasMentionsAndGetMentions() throws InterruptedException {
        MentionDetector mentionDetector = spy(new MentionDetector("a text"));

        mentionDetector.hasMentions();
        mentionDetector.getMentions();

        verify(mentionDetector, times(1)).parseMentions();
    }

    @Test
    public void shouldCallParseTextOnce_whenCallingHasMentionsAndGetSequences() throws InterruptedException {
        MentionDetector mentionDetector = spy(new MentionDetector("a text"));

        mentionDetector.hasMentions();
        mentionDetector.getSequences();

        verify(mentionDetector, times(1)).parseMentions();
    }

    @Test
    public void shouldCallParseTextOnce_whenCallingGetSequencesTwice() throws InterruptedException {
        MentionDetector mentionDetector = spy(new MentionDetector("a text"));

        mentionDetector.getSequences();
        mentionDetector.getSequences();

        verify(mentionDetector, times(1)).parseMentions();
    }
}
