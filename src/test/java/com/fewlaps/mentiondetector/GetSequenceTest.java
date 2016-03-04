package com.fewlaps.mentiondetector;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetSequenceTest {

    @Test
    public void shouldReturnEmpty_whenTextDoesntHaveAnyMention() throws InterruptedException {
        String text = "hello";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).isEmpty();
    }

    @Test
    public void shouldReturnASequence_whenTextHasOneMention() throws InterruptedException {
        String text = "hello @roc";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(1);
    }

    @Test
    public void shouldReturnASequence_whenTextHasTwoMentionsWrittenTogether() throws InterruptedException {
        String text = "hello @roc @esteve";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(1);
    }

    @Test
    public void shouldReturnTwoSequences_whenTextHasTwoMentionsWrittenSeparate() throws InterruptedException {
        String text = "hello @roc and @esteve";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(2);
    }

    @Test
    public void shouldReturnTwoSequences_whenTextHasThreeMentions_andFirstIsSeparatedOfSecondAndThird() throws InterruptedException {
        String text = "hello @roc and @esteve @aguilera";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(2);
    }

    @Test
    public void shouldReturnOneSequence_whenTextHasThreeMentions_separatedOnlyByLotsOfWhitespaces() throws InterruptedException {
        String text = "hello @roc    @boronat       @esteve        @aguilera";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(1);
    }
}
