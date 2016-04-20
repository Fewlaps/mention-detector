package com.fewlaps.mentiondetector;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class IssuesFoundAtQuitNowTest {

    @Test
    public void getMentions_shouldReturnGoodStartIndex_whenTheresAAtSymbol_farAwayOfTheNickName() throws InterruptedException {
        String text = "@Twinx@ Texie@sabertooth89 great Quit!!";
        int texieStart = new MentionDetector(text).getMentions().get(1).start();
        assertThat(texieStart).isEqualTo(6);
    }

    @Test
    public void getMentions_shouldNotCrash_whenPassingAQuitNowText1() throws InterruptedException {
        String text = "@Twinx@ Texie@sabertooth89 great Quit!!";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).isNotEmpty();
    }

    @Test
    public void getSequences_shouldNotCrash_whenPassingAQuitNowText1() throws InterruptedException {
        String text = "@Twinx@ Texie@sabertooth89 great Quit!!";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).isNotEmpty();
    }

}
