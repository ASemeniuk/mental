package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class Simple2DigitSequenceGeneratorTest {

    private SequenceGenerator generator;
    private final int LIMIT = 6;

    @Before
    public void initialize() {
        generator = new Simple2DigitSequenceGenerator(LIMIT);
    }

    @Test
    public void testSequence() {
        generator.initialize();
        for (int i =0; i < 100; i++) {
            int number = generator.getNextNumber();
            assertTrue("Generated number is 1-digit: " + number, Math.abs(number) >= 10);
            int result = generator.getCurrentResult();
            assertTrue("Negative result: " + result, result >= 0);
            assertTrue("Result out of 2-digit bound: " + result, result <= 99);
            assertTrue("Result breaks limit: " + result, result / 10 <= LIMIT);
            assertTrue("Result breaks limit: " + result, result % 10 <= LIMIT);
        }
    }

}
