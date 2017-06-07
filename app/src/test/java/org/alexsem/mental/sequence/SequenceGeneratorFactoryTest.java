package org.alexsem.mental.sequence;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Test {@link SequenceGeneratorFactory} class
 */

public class SequenceGeneratorFactoryTest {

    @Test
    public void testSimple1Digit() {
        SequenceGenerator generator = SequenceGeneratorFactory.newGenerator(SequenceGeneratorFactory.MODE_SIMPLE, SequenceGeneratorFactory.DIFF_SINGLE_DIGIT);
        int number = generator.getNextNumber();
        assertTrue("Not a 1-digit generator", number >= 1);
        assertTrue("Not a 1-digit generator", number <= 9);
    }

    @Test
    public void testSimpleDecades() {
        SequenceGenerator generator = SequenceGeneratorFactory.newGenerator(SequenceGeneratorFactory.MODE_SIMPLE, SequenceGeneratorFactory.DIFF_DECADES);
        int number = generator.getNextNumber();
        assertEquals("Not a decades generator", 0, number % 10);
    }

    @Test
    public void testSimple2Digit() {
        SequenceGenerator generator = SequenceGeneratorFactory.newGenerator(SequenceGeneratorFactory.MODE_SIMPLE, SequenceGeneratorFactory.DIFF_DOUBLE_DIGIT);
        int number = generator.getNextNumber();
        assertTrue("Not a 2-digit generator", number >= 10);
        assertTrue("Not a 2-digit generator", number <= 99);
    }

    @Test
    public void testSimpleHundreds() {
        SequenceGenerator generator = SequenceGeneratorFactory.newGenerator(SequenceGeneratorFactory.MODE_SIMPLE, SequenceGeneratorFactory.DIFF_HUNDREDS);
        int number = generator.getNextNumber();
        assertEquals("Not a hundreds generator", 0, number % 100);
    }


    //TODO add other generators

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrect() {
        SequenceGeneratorFactory.newGenerator(SequenceGeneratorFactory.DIFF_DOUBLE_DIGIT, SequenceGeneratorFactory.MODE_SIMPLE);
    }

}
