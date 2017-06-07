package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class SimpleDecadeSequenceGeneratorTest {

    private SequenceGenerator generator;
    private final int LIMIT = 4;

    @Before
    public void initialize() {
        generator = new SimpleDecadeSequenceGenerator(LIMIT);
    }

    @Test
    public void testSequence() {
        generator.initialize();
        for (int i =0; i < 100; i++) {
            generator.getNextNumber();
            assertTrue("Negative result", generator.getCurrentResult() >= 0);
            assertTrue("Result out of bounds", generator.getCurrentResult() <= 90);
            assertEquals("Not a decade", 0, generator.getCurrentResult() % 10);
            assertTrue("Result breaks limit", generator.getCurrentResult() <= LIMIT * 10);
        }
    }

}
