package com.fewlaps.mentiondetector;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RemovePunctuationMarksTest {

    RemovePunctuationMarks removePunctuationMarks;

    @Before
    public void setup() {
        removePunctuationMarks = new RemovePunctuationMarks();
    }

    @Test
    public void shouldRemoveExclamationMarks() throws InterruptedException {
        String textWithPunctuationMarks = "hello!!!!";

        String textWithoutPunctuationMarks = removePunctuationMarks.removePunctuationMarks(textWithPunctuationMarks);

        assertThat(textWithoutPunctuationMarks).doesNotContain("!");
    }

    @Test
    public void shouldRemoveQuestionMarks() throws InterruptedException {
        String textWithPunctuationMarks = "hello????";

        String textWithoutPunctuationMarks = removePunctuationMarks.removePunctuationMarks(textWithPunctuationMarks);

        assertThat(textWithoutPunctuationMarks).doesNotContain("?");
    }

    @Test
    public void shouldRemoveReversedExclamationMarks() throws InterruptedException {
        String textWithPunctuationMarks = "hello¡¡¡¡";

        String textWithoutPunctuationMarks = removePunctuationMarks.removePunctuationMarks(textWithPunctuationMarks);

        assertThat(textWithoutPunctuationMarks).doesNotContain("¡");
    }

    @Test
    public void shouldRemoveReversedQuestionMarks() throws InterruptedException {
        String textWithPunctuationMarks = "hello¿¿¿";

        String textWithoutPunctuationMarks = removePunctuationMarks.removePunctuationMarks(textWithPunctuationMarks);

        assertThat(textWithoutPunctuationMarks).doesNotContain("¿");
    }
}
