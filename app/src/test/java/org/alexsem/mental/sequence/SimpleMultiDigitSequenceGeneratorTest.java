package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class SimpleMultiDigitSequenceGeneratorTest {

    private SequenceGenerator generator;
    private final int DIGITS = 5;
    private final int LIMIT = 6;

    @Before
    public void initialize() {
        generator = new SimpleMultiDigitSequenceGenerator(DIGITS, LIMIT);
    }

    @Test
    public void testSequence() {
        generator.initialize();
        for (int i =0; i < 100; i++) {
            int number = generator.getNextNumber();
            assertTrue("Generated number is not 5-digit: " + number, Math.abs(number) >= 10000);
            int result = generator.getCurrentResult();
            assertTrue("Negative result: " + result, result >= 0);
            assertTrue("Result out of 5-digit bound: " + result, result <= 99999);
            int temp = result;
            for (int j = 1;j < 5; j++){
                assertTrue("Result breaks limit: " + result, temp % 10 <= LIMIT);
                temp /= 10;
            }
        }
    }

}
