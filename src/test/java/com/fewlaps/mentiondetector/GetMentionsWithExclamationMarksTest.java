package com.fewlaps.mentiondetector;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetMentionsWithExclamationMarksTest {

    @Test
    public void shouldIgnoreExclamationMark() throws InterruptedException {
        String text = "hello @roc!";
        String username = new MentionDetector(text).getMentions().get(0).username();
        assertThat(username).isEqualTo("@roc");
    }

    @Test
    public void shouldIgnoreQuestionMark() throws InterruptedException {
        String text = "hello @roc?";
        String username = new MentionDetector(text).getMentions().get(0).username();
        assertThat(username).isEqualTo("@roc");
    }
}
