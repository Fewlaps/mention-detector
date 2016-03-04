package com.fewlaps.mentiondetector;

import org.junit.Test;

public class ConstructorTest {

    @Test(expected = NullPointerException.class)
    public void shouldLaunchNpe_whenCallingTheBuilderWithNull() throws InterruptedException {
        MentionDetector detector = new MentionDetector(null);
    }

    @Test
    public void shouldWork_whenCallingTheBuilderWithBlank() throws InterruptedException {
        MentionDetector detector = new MentionDetector("");
    }

    @Test
    public void shouldWork_whenCallingTheBuilderWithWhiteSpace() throws InterruptedException {
        MentionDetector detector = new MentionDetector(" ");
    }

    @Test
    public void shouldWork_whenCallingTheBuilderWithAText() throws InterruptedException {
        MentionDetector detector = new MentionDetector("a text");
    }
}
