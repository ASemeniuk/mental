package org.alexsem.mental.sequence;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class NumberGeneratorTest {


    @Test
    public void test4Digit() {
        NumberGenerator generator = new NumberGenerator(4, 9);
        for (int i = 0; i < 20; i++) {
            int number = generator.generate();
            assertTrue("Negative number", number >= 0);
            assertTrue("Not a 4 digit number", number < 10000);
        }
    }

    @Test
    public void testRandomSequence() {
        NumberGenerator generator = new NumberGenerator(1, 9);
        int lastNumber = -1;
        for (int i = 0; i < 30; i++) {
            int number = generator.generate();
            assertNotEquals("Number repeats", lastNumber, number);
            lastNumber = number;
        }
    }

    @Test
    public void test2Digit() {
        NumberGenerator generator = new NumberGenerator(2, 5);
        for (int i = 0; i < 20; i++) {
            int number = generator.generate();
            assertTrue("Negative number", number >= 0);
            assertTrue("Not a 2-digit number", number <= 55);
            assertTrue("Limit broken: 5", number / 10 <= 5);
            assertTrue("Limit broken: 5", number % 10 <= 5);
        }
    }

}
