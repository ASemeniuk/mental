package org.alexsem.mental.sequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SequenceLoggerTest {

    private SequenceLogger logger;

    @Before
    public void initialize() {
        logger = new SequenceLogger();
    }

    @Test
    public void testSequence() {
        logger.reset();
        logger.log(10);
        assertEquals("10", logger.print());
        logger.log(10);
        assertEquals("10 + 10", logger.print());
        logger.logResult(20);
        assertEquals("10 + 10 = 20", logger.print());

        logger.reset();
        logger.log(-10);
        assertEquals(3, logger.print().length());
        logger.log(+5);
        logger.log(-2);
        assertEquals("-10 + 5 - 2", logger.print());
    }

    @Test(expected = IllegalStateException.class)
    public void testClosure() {
        logger.reset();
        logger.logResult(20);
        logger.log(10);
    }

    @Test(expected = IllegalStateException.class)
    public void testClosure2() {
        logger.reset();
        logger.logResult(20);
        logger.logResult(30);
    }

}
