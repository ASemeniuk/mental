package org.alexsem.mental.sequence;

import java.util.Random;

class Simple1DigitSequenceGenerator implements SequenceGenerator {

    private int currentResult;
    private Random random;
    private int limit;

    public Simple1DigitSequenceGenerator(int limit) {
        random = new Random();
        currentResult = 0;
        this.limit = limit;
    }

    private final int[][] SEQUENCE = {
            /*0*/ {+1, +2, +3, +4, +5, +6, +7, +8, +9},
            /*1*/ {-1, +1, +2, +3, +5, +6, +7, +8},
            /*2*/ {-2, -1, +1, +2, +5, +6, +7},
            /*3*/ {-3, -2, -1, +1, +5, +6},
            /*4*/ {-4, -3, -2, -1, +5},
            /*5*/ {-5, +1, +2, +3, +4},
            /*6*/ {-6, -5, -1, +1, +2, +3},
            /*7*/ {-7, -6, -5, -2, -1, +1, +2},
            /*8*/ {-8, -7, -6, -5, -3, -2, -1, +1},
            /*9*/ {-9, -8, -7, -6, -5, -4, -3, -2, -1}
    };

    @Override
    public void initialize() {
        currentResult = 0;
    }

    @Override
    public int getNextNumber() {
        int nextNumber;
        do {
            nextNumber = SEQUENCE[currentResult][random.nextInt(SEQUENCE[currentResult].length)];
        } while (currentResult + nextNumber > limit);
        currentResult += nextNumber;
        return nextNumber;
    }

    @Override
    public int getCurrentResult() {
        return currentResult;
    }


}
