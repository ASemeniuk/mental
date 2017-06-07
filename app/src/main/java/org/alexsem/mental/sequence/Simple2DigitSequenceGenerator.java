package org.alexsem.mental.sequence;

import java.util.Random;

class Simple2DigitSequenceGenerator implements SequenceGenerator {

    private int currentResult;
    private Random random;
    private int limit;

    public Simple2DigitSequenceGenerator(int limit) {
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
        int currentResult1 = currentResult / 10;
        int currentResult2 = currentResult % 10;
        int nextNumber1;
        int nextNumber2;

        do {
            do {
                nextNumber1 = SEQUENCE[currentResult1][random.nextInt(SEQUENCE[currentResult1].length)];
            } while (currentResult1 + nextNumber1 > limit);

            do {
                nextNumber2 = SEQUENCE[currentResult2][random.nextInt(SEQUENCE[currentResult2].length)];
            } while (currentResult2 + nextNumber2 > limit);
        } while (nextNumber1 * nextNumber2 < 0);

        int nextNumber = nextNumber1 * 10 + nextNumber2;
        currentResult += nextNumber;
        return nextNumber;
    }

    @Override
    public int getCurrentResult() {
        return currentResult;
    }

}
