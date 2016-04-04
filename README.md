[![Build Status](https://travis-ci.org/Fewlaps/mention-detector.svg?branch=master)](https://travis-ci.org/Fewlaps/mention-detector)
[![Coverage Status](https://coveralls.io/repos/github/Fewlaps/mention-detector/badge.svg?branch=master)](https://coveralls.io/github/Fewlaps/mention-detector?branch=master)

# mention-detector
Detect **@mentions** and **@sequence @of @mentions** with this fully tested library.

```java
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
```

#Download

* Get <a href="https://github.com/Fewlaps/mention-detector/releases/download/1.2.0/mention-detector-1.2.0.jar">the latest .jar</a> 

* Grab via Gradle:
```groovy
repositories { jcenter() }
    
compile 'com.fewlaps.mentiondetector:mention-detector:1.2.0'
```
* Grab via Maven:
```xml
<repository>
  <id>jcenter</id>
  <url>http://jcenter.bintray.com</url>
</repository>

<dependency>
    <groupId>com.fewlaps.mentiondetector</groupId>
    <artifactId>mention-detector</artifactId>
    <version>1.2.0</version>
</dependency>
```

## LICENSE ##

The MIT License (MIT)

Copyright (c) 2016 Fewlaps

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
