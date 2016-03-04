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

    @Test
    public void shouldIgnoreStartingParenthesis() throws InterruptedException {
        String text = "hello (@roc";
        String username = new MentionDetector(text).getMentions().get(0).username();
        assertThat(username).isEqualTo("@roc");
    }

    @Test
    public void shouldIgnoreStartingBracket() throws InterruptedException {
        String text = "hello {@roc";
        String username = new MentionDetector(text).getMentions().get(0).username();
        assertThat(username).isEqualTo("@roc");
    }

    @Test
    public void shouldIgnoreWrappingParenthesis() throws InterruptedException {
        String text = "hello (@roc)";
        String username = new MentionDetector(text).getMentions().get(0).username();
        assertThat(username).isEqualTo("@roc");
    }

    @Test
    public void shouldReturnFalse_whenPassingBlank() throws InterruptedException {
        String text = "";
        boolean hasMentions = new MentionDetector(text).hasMentions();
        assertThat(hasMentions).isFalse();
    }

    @Test
    public void shouldReturnFalse_whenPassingATextWithoutMentions() throws InterruptedException {
        String text = "a text without mentions";
        boolean hasMentions = new MentionDetector(text).hasMentions();
        assertThat(hasMentions).isFalse();
    }

    @Test
    public void shouldReturnTrue_whenPassingATextWithAMention() throws InterruptedException {
        String text = "I want to mention @rocboronat";
        boolean hasMentions = new MentionDetector(text).hasMentions();
        assertThat(hasMentions).isTrue();
    }
}
