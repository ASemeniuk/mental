package org.alexsem.mental.sequence;

import java.util.Random;

class SimpleHundredSequenceGenerator implements SequenceGenerator {

    private int currentResult;
    private Random random;
    private int limit;

    public SimpleHundredSequenceGenerator(int limit) {
        random = new Random();
        currentResult = 0;
        this.limit = limit * 100;
    }

    private final int[][] SEQUENCE = {
            /*00*/ {+100, +200, +300, +400, +500, +600, +700, +800, +900},
            /*10*/ {-100, +100, +200, +300, +500, +600, +700, +800},
            /*20*/ {-200, -100, +100, +200, +500, +600, +700},
            /*30*/ {-300, -200, -100, +100, +500, +600},
            /*40*/ {-400, -300, -200, -100, +500},
            /*50*/ {-500, +100, +200, +300, +400},
            /*60*/ {-600, -500, -100, +100, +200, +300},
            /*70*/ {-700, -600, -500, -200, -100, +100, +200},
            /*80*/ {-800, -700, -600, -500, -300, -200, -100, +100},
            /*90*/ {-900, -800, -700, -600, -500, -400, -300, -200, -100}
    };


    @Override
    public void initialize() {
        currentResult = 0;
    }

    @Override
    public int getNextNumber() {
        int nextNumber;
        do {
            nextNumber = SEQUENCE[currentResult / 100][random.nextInt(SEQUENCE[currentResult / 100].length)];
        } while (currentResult + nextNumber > limit);
        currentResult += nextNumber;
        return nextNumber;
    }

    @Override
    public int getCurrentResult() {
        return currentResult;
    }


}
