package com.fewlaps.mentiondetector;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TwoLinesMentionsTest {

    @Test
    public void oneMentionInOneLine() {
        String oneLineMention = "@Roc hello";

        List<Mention> mentions = new MentionDetector(oneLineMention).getMentions();

        assertThat(mentions).hasSize(1);
        assertThat(mentions.get(0).end()).isEqualTo(4);
    }

    @Test
    public void oneMentionWithABreakingSpace() {
        String oneLineMention = "@Roc\nhello";

        List<Mention> mentions = new MentionDetector(oneLineMention).getMentions();

        assertThat(mentions).hasSize(1);
        assertThat(mentions.get(0).start()).isEqualTo(0);
        assertThat(mentions.get(0).end()).isEqualTo(4);
    }

    @Test
    public void twoMentionsWithABreakingSpace() {
        String oneLineMention = "@Roc\n@Esteve";

        List<Mention> mentions = new MentionDetector(oneLineMention).getMentions();

        assertThat(mentions).hasSize(2);
        assertThat(mentions.get(0).start()).isEqualTo(0);
        assertThat(mentions.get(0).end()).isEqualTo(4);
        assertThat(mentions.get(1).start()).isEqualTo(5);
        assertThat(mentions.get(1).end()).isEqualTo(12);
    }

    @Test
    public void twoMentionsWithABreakingSpaceAndSomePadding() {
        String oneLineMention = "@Roc\nhello @Esteve";

        List<Mention> mentions = new MentionDetector(oneLineMention).getMentions();

        assertThat(mentions).hasSize(2);
        assertThat(mentions.get(0).start()).isEqualTo(0);
        assertThat(mentions.get(0).end()).isEqualTo(4);
        assertThat(mentions.get(1).start()).isEqualTo(11);
        assertThat(mentions.get(1).end()).isEqualTo(18);
    }

    @Test
    public void oneMentionInTwoLinesEndingWithTheMention() {
        String oneLineMention = "hello\n@Esteve";

        List<Mention> mentions = new MentionDetector(oneLineMention).getMentions();

        assertThat(mentions).hasSize(1);
        assertThat(mentions.get(0).start()).isEqualTo(6);
        assertThat(mentions.get(0).end()).isEqualTo(13);
    }
}
