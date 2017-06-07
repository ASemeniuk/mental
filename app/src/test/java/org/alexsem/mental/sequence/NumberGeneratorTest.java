package org.alexsem.mental.sequence;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class NumberGeneratorTest {


    @Test
    public void test4Digit() {
        NumberGenerator generator = new NumberGenerator(4);
        for (int i = 0; i < 20; i++) {
            int number = generator.generate();
            assertTrue(number >= 0);
            assertTrue(number < 10000);
        }
    }

    @Test
    public void testRandomSequence() {
        NumberGenerator generator = new NumberGenerator(1);
        int lastNumber = -1;
        for (int i = 0; i < 30; i++) {
            int number = generator.generate();
            assertNotEquals(lastNumber, number);
            lastNumber = number;
        }
    }

    @Test
    public void test2Digit() {
        NumberGenerator generator = new NumberGenerator(2);
        for (int i = 0; i < 20; i++) {
            int number = generator.generate();
            assertTrue(number >= 0);
            assertTrue(number < 100);
        }
    }

}
