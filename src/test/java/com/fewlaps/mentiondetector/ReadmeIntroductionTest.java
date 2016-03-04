package com.fewlaps.mentiondetector;

import org.junit.Test;

public class ReadmeIntroductionTest {

    @Test
    public void introduction() throws InterruptedException {
        String text = "Hello! This is a great introduction to @MentionDetector!";

        //With MentionDetector you can... well. You can detect mentions!
        MentionDetector detector = new MentionDetector(text);

        detector.hasMentions(); //Returns if the text has any mention
        detector.getMentions(); //Returns a list with the mention to @MentionDetector

        Mention mention = detector.getMentions().get(0); //If we get a mention, we could ask:
        mention.start(); //Where the mention starts? At 39!
        mention.end(); //Where the mention ends? At 55!
        mention.username(); //What's the mentioned username? @MentionDetector
        mention.usernameWithoutAtSymbol(); //And what's the actual username? MentionDetector

        //In addition, you can also get the mention sequences.
        detector.getSequences();

        //A sequence is a list of mentions only separated by spaces. For example:
        String fourMentions = "Hello! I love @trains @bananas @pizza and @sushi";
        //In this case, "@trains @bananas @pizza" is one sequence, and "@sushi" is another one.

        Sequence sequence = new MentionDetector(fourMentions).getSequences().get(0);
        sequence.start(); //Where the sequence starts? At 14!
        sequence.end(); //Where the sequence ends? At 37!

        //Now, you can create one StyleSpan instead of three. And that's great for performance!
    }
}
