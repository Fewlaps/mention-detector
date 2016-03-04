package com.fewlaps.mentiondetector;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MentionTest {

    Mention mention;

    @Before
    public void setup() {
        mention = new Mention("@roc", 0, 4);
    }

    @Test
    public void shouldReturnUsernameWithAtSymbol_whenSimpleUsernameMethodIsCalled() throws InterruptedException {
        String username = mention.username();

        assertThat(username).contains("@");
    }

    @Test
    public void shouldReturnUsernameWithoutAtSymbol_whenUsernameWithoutAtSymbolMethodIsCalled() throws InterruptedException {
        String username = mention.usernameWithoutAtSymbol();

        assertThat(username).doesNotContain("@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLaunchAnIllegalArgumentException_whenCreatedWithoutAtSymbol() throws InterruptedException {
        new Mention("roc", 0, 4);
    }
}
