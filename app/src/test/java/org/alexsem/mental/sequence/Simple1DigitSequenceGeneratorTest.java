package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class Simple1DigitSequenceGeneratorTest {

    private SequenceGenerator generator;
    private final int LIMIT = 6;

    @Before
    public void initialize() {
        generator = new Simple1DigitSequenceGenerator(LIMIT);
    }

    @Test
    public void testSequence() {
        generator.initialize();
        for (int i =0; i < 100; i++) {
            generator.getNextNumber();
            assertTrue("Negative result", generator.getCurrentResult() >= 0);
            assertTrue("Result out of 1-digit bound", generator.getCurrentResult() <= 9);
            assertTrue("Result breaks limit", generator.getCurrentResult() <= LIMIT);
        }
    }

}
