package org.alexsem.mental.sequence;

/**
 *  Used to generate various sequences
 */
public interface SequenceGenerator {

    /**
     * Clears current result and prepares generator for the new sequence
     */
    void initialize();

    /**
     * Generates next number in the sequence
     * @return Next number
     */
    int getNextNumber();

    /**
     * Returns current sum of all generated numbers
     * @return Current result
     */
    int getCurrentResult();

}
