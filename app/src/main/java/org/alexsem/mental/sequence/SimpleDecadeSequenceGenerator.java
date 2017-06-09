package org.alexsem.mental.sequence;

import java.util.Random;

class SimpleDecadeSequenceGenerator implements SequenceGenerator {

    private final int LIMIT;
    private int currentResult;
    private Random random;

    public SimpleDecadeSequenceGenerator(int limit) {
        this.LIMIT = limit * 10;
        random = new Random();
        currentResult = 0;
    }

    private final int[][] SEQUENCE = {
            /*00*/ {+10, +20, +30, +40, +50, +60, +70, +80, +90},
            /*10*/ {-10, +10, +20, +30, +50, +60, +70, +80},
            /*20*/ {-20, -10, +10, +20, +50, +60, +70},
            /*30*/ {-30, -20, -10, +10, +50, +60},
            /*40*/ {-40, -30, -20, -10, +50},
            /*50*/ {-50, +10, +20, +30, +40},
            /*60*/ {-60, -50, -10, +10, +20, +30},
            /*70*/ {-70, -60, -50, -20, -10, +10, +20},
            /*80*/ {-80, -70, -60, -50, -30, -20, -10, +10},
            /*90*/ {-90, -80, -70, -60, -50, -40, -30, -20, -10}
    };


    @Override
    public void initialize() {
        currentResult = 0;
    }

    @Override
    public int getNextNumber() {
        int nextNumber;
        do {
            nextNumber = SEQUENCE[currentResult / 10][random.nextInt(SEQUENCE[currentResult / 10].length)];
        } while (currentResult + nextNumber > LIMIT);
        currentResult += nextNumber;
        return nextNumber;
    }

    @Override
    public int getCurrentResult() {
        return currentResult;
    }


}
