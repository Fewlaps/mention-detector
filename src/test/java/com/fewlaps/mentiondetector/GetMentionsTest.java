package com.fewlaps.mentiondetector;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GetMentionsTest {

    @Test
    public void shouldReturnEmpty_whenPassingBlank() throws InterruptedException {
        String text = "";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).isEmpty();
    }

    @Test
    public void shouldReturnEmpty_whenPassingATextWithoutMentions() throws InterruptedException {
        String text = "a text without mentions";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).isEmpty();
    }

    @Test
    public void shouldReturnAMention_whenPassingATextWithAMention() throws InterruptedException {
        String text = "hello @roc";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).hasSize(1);
    }

    @Test
    public void shouldReturnTwoMentions_whenPassingATextWithTwoMentions() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).hasSize(2);
    }

    @Test
    public void shouldReturnTwoMentions_whenPassingATextWithTwoMentions_andNothingElse() throws InterruptedException {
        String text = "@roc @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).hasSize(2);
    }
}
