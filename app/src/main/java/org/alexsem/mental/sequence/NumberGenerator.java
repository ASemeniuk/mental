package org.alexsem.mental.sequence;

import java.util.Random;

/**
 * Generates random numbers
 */
public class NumberGenerator {

    private Random random;

    private int maxValueBound;
    private int currentNumber;

    public NumberGenerator(int numberOfDigits) {
        this.currentNumber = -1;
        this.maxValueBound = 1;
        for (int i = 0; i < numberOfDigits; i++) {
            maxValueBound *= 10;
        }
        this.random = new Random();
    }

    /**
     * Generate next number
     * @return Pseudo-random number
     */
    public int generate() {
        int number;
        do {
            number = random.nextInt(maxValueBound);
        } while (number == currentNumber);
        currentNumber = number;
        return number;
    }
}
