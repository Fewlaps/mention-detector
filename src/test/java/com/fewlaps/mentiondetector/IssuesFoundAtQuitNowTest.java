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
        assertThat(mentions).hasSize(3);
    }

    @Test
    public void getSequences_shouldNotCrash_whenPassingAQuitNowText1() throws InterruptedException {
        String text = "@Twinx@ Texie@sabertooth89 great Quit!!";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).isNotEmpty();
    }

    @Test
    public void getMentions_shouldNotCrash_whenPassingAQuitNowText2() throws InterruptedException {
        String text = "@Baub @bratt @florico @Mohams @menino @karyteatime @veuxrespirer @Stopcettemerde @vilain @severinnnne @ @help63 @john81bel @ Bonsoir \uF60E\uF60E\uF60E";
        List<Mention> mentions = new MentionDetector(text).getMentions();
        assertThat(mentions).hasSize(13);
    }

    @Test
    public void getSequences_shouldNotCrash_whenPassingAQuitNowText2() throws InterruptedException {
        String text = "@Baub @bratt @florico @Mohams @menino @karyteatime @veuxrespirer @Stopcettemerde @vilain @severinnnne @ @help63 @john81bel @ Bonsoir \uF60E\uF60E\uF60E";
        List<Sequence> sequences = new MentionDetector(text).getSequences();
        assertThat(sequences).hasSize(2);
    }
}
