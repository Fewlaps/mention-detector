package com.fewlaps.mentiondetector;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetMentionsWithPositionTest {

    @Test
    public void shouldReturnStart6_whenMentionStartsAtPosition6() throws InterruptedException {
        String text = "hello @roc";
        int start = new MentionDetector(text).getMentions().get(0).start();
        assertThat(start).isEqualTo(6);
    }

    @Test
    public void shouldReturnEnd11_whenMentionEndsAtPosition11() throws InterruptedException {
        String text = "hello @roc";
        int end = new MentionDetector(text).getMentions().get(0).end();
        assertThat(end).isEqualTo(10);
    }

    @Test
    public void shouldReturnStart6forRoc_whenThereAreTwoMentions_andRocStartsAt6() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();

        int rocStart = mentions.get(0).start();

        assertThat(rocStart).isEqualTo(6);
    }

    @Test
    public void shouldReturnEnd10forRoc_whenThereAreTwoMentions_andRocEndsAt10() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();

        int rocEnd = mentions.get(0).end();

        assertThat(rocEnd).isEqualTo(10);
    }

    @Test
    public void shouldReturnStart15forEsteve_whenThereAreTwoMentions_andEsteveStartsAt15() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();

        int esteveStart = mentions.get(1).start();

        assertThat(esteveStart).isEqualTo(15);
    }

    @Test
    public void shouldReturnEnd22forEsteve_whenThereAreTwoMentions_andEsteveEndsAt22() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Mention> mentions = new MentionDetector(text).getMentions();

        int esteveEnd = mentions.get(1).end();

        assertThat(esteveEnd).isEqualTo(22);
    }
}
