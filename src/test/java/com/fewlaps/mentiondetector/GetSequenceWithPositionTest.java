package com.fewlaps.mentiondetector;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetSequenceWithPositionTest {

    @Test
    public void shouldReturnStart6_whenSequenceStartsAtPosition6() throws InterruptedException {
        String text = "hello @roc @esteve";
        int start = new MentionDetector(text).getSequences().get(0).start();
        assertThat(start).isSameAs(6);
    }

    @Test
    public void shouldReturnEnd18_whenSequenceEndsAtPosition18() throws InterruptedException {
        String text = "hello @roc @esteve";
        int end = new MentionDetector(text).getSequences().get(0).end();
        assertThat(end).isSameAs(18);
    }

    @Test
    public void shouldReturnStart6_whenSequenceStartsAtPosition6_andThereAreAnotherSequence() throws InterruptedException {
        String text = "hello @roc @esteve and my friend @banana";
        int start = new MentionDetector(text).getSequences().get(0).start();
        assertThat(start).isSameAs(6);
    }

    @Test
    public void shouldReturnEnd18_whenSequenceEndsAtPosition18_andThereAreAnotherSequence() throws InterruptedException {
        String text = "hello @roc @esteve and my friend @banana";
        int end = new MentionDetector(text).getSequences().get(0).end();
        assertThat(end).isSameAs(18);
    }
}
