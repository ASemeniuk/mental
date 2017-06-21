package org.alexsem.mental.sequence;

import java.util.Random;

/**
 * Generates random numbers
 */
public class NumberGenerator {

    private final int DIGITS;
    private final int LIMIT;
    private Random random;

    private int currentNumber;

    public NumberGenerator(int numberOfDigits, int limit) {
        this.DIGITS = numberOfDigits;
        this.LIMIT = limit;
        this.currentNumber = -1;
        this.random = new Random();
    }

    /**
     * Generate next number
     * @return Pseudo-random number
     */
    public int generate() {
        int number;
        do {
            number = 0;
            for (int i = 0; i < DIGITS; i++) {
                number *= 10;
                number += random.nextInt(LIMIT + 1);
            }
        } while (number == currentNumber);
        currentNumber = number;
        return number;
    }
}
