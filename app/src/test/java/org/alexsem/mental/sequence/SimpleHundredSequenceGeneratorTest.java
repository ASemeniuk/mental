package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class SimpleHundredSequenceGeneratorTest {

    private SequenceGenerator generator;
    private final int LIMIT = 6;

    @Before
    public void initialize() {
        generator = new SimpleHundredSequenceGenerator(LIMIT);
    }

    @Test
    public void testSequence() {
        generator.initialize();
        for (int i =0; i < 100; i++) {
            generator.getNextNumber();
            assertTrue("Negative result", generator.getCurrentResult() >= 0);
            assertTrue("Result out of bounds", generator.getCurrentResult() <= 900);
            assertEquals("Not a hundred", 0, generator.getCurrentResult() % 100);
            assertTrue("Result breaks limit", generator.getCurrentResult() <= LIMIT * 100);
        }
    }

}
