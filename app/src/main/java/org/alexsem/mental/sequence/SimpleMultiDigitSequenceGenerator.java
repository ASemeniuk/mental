package org.alexsem.mental.sequence;

import java.util.Random;

class SimpleMultiDigitSequenceGenerator implements SequenceGenerator {

    private final int DIGITS;
    private final int LIMIT;

    private int currentResult;
    private Random random;

    public SimpleMultiDigitSequenceGenerator(int numberOfDigits, int limit) {
        this.DIGITS = numberOfDigits;
        this.LIMIT = limit;
        random = new Random();
        currentResult = 0;
    }

    private final int[][][] SEQUENCES_PLUS = {
            //LIMIT 0
            {},
            //LIMIT 1
            {
            /*0*/ {+1}
            },
            //LIMIT 2
            {
            /*0*/ {+1, +2},
            /*1*/ {+1}
            },
            //LIMIT 3
            {
            /*0*/ {+1, +2, +3},
            /*1*/ {+1, +2},
            /*2*/ {+1}
            },
            //LIMIT 4
            {
            /*0*/ {+1, +2, +3, +4},
            /*1*/ {+1, +2, +3},
            /*2*/ {+1, +2},
            /*3*/ {+1}
            },
            //LIMIT 5
            {
            /*0*/ {+1, +2, +3, +4, +5},
            /*1*/ {+1, +2, +3},
            /*2*/ {+1, +2},
            /*3*/ {+1}
            },
            //LIMIT 6
            {
            /*0*/ {+1, +2, +3, +4, +5, +6},
            /*1*/ {+1, +2, +3, +5},
            /*2*/ {+1, +2},
            /*3*/ {+1},
            /*4*/ {},
            /*5*/ {+1},
            },
            //LIMIT 7
            {
            /*0*/ {+1, +2, +3, +4, +5, +6, +7},
            /*1*/ {+1, +2, +3, +5, +6},
            /*2*/ {+1, +2, +5},
            /*3*/ {+1},
            /*4*/ {},
            /*5*/ {+1, +2},
            /*6*/ {+1}
            },
            //LIMIT 8
            {
            /*0*/ {+1, +2, +3, +4, +5, +6, +7, +8},
            /*1*/ {+1, +2, +3, +5, +6, +7},
            /*2*/ {+1, +2, +5, +6},
            /*3*/ {+1, +5},
            /*4*/ {},
            /*5*/ {+1, +2, +3},
            /*6*/ {+1, +2},
            /*7*/ {+1},
            },
            //LIMIT 9
            {
            /*0*/ {+1, +2, +3, +4, +5, +6, +7, +8, +9},
            /*1*/ {+1, +2, +3, +5, +6, +7, +8},
            /*2*/ {+1, +2, +5, +6, +7},
            /*3*/ {+1, +5, +6},
            /*4*/ {+5},
            /*5*/ {+1, +2, +3, +4},
            /*6*/ {+1, +2, +3},
            /*7*/ {+1, +2},
            /*8*/ {+1},
            }
    };

    private final int[][] SEQUENCE_MINUS = {
            /*0*/ {},
            /*1*/ {-1},
            /*2*/ {-2, -1},
            /*3*/ {-3, -2, -1},
            /*4*/ {-4, -3, -2, -1},
            /*5*/ {-5},
            /*6*/ {-6, -5, -1},
            /*7*/ {-7, -6, -5, -2, -1},
            /*8*/ {-8, -7, -6, -5, -3, -2, -1},
            /*9*/ {-9, -8, -7, -6, -5, -4, -3, -2, -1}
    };

    @Override
    public void initialize() {
        currentResult = 0;
    }

    @Override
    public int getNextNumber() {

        boolean plusPossible = true;
        boolean minusPossible = true;
        int plusSum = 0;
        int minusSum = 0;

        int currentResults[] = new int[DIGITS];
        int temp = currentResult;
        for (int i = 1; i <= DIGITS; i++) {
            int modus = temp % 10;
            if (modus == 0) {
                minusPossible = false;
            }
            if (modus >= LIMIT || SEQUENCES_PLUS[LIMIT][modus].length == 0) {
                plusPossible = false;
            }
            plusSum += SEQUENCES_PLUS[modus].length;
            minusSum += SEQUENCE_MINUS[modus].length;
            currentResults[DIGITS - i] = modus;
            temp /= 10;
        }

        int[] nextNumbers = new int[DIGITS];

        boolean sign;
        if (plusPossible && minusPossible) {
            sign = (random.nextInt(minusSum + plusSum) >= minusSum);
        } else {
            sign = plusPossible;
        }

        for (int i = 0; i < DIGITS; i++) {
            int currentDigit = currentResults[i];
            do {
                if (sign) {
                    nextNumbers[i] = SEQUENCES_PLUS[LIMIT][currentDigit][random.nextInt(SEQUENCES_PLUS[LIMIT][currentDigit].length)];
                } else {
                    nextNumbers[i] = SEQUENCE_MINUS[currentDigit][random.nextInt(SEQUENCE_MINUS[currentDigit].length)];
                }
            } while (currentDigit + nextNumbers[i] > LIMIT);
        }

        int nextNumber = 0;
        for (int i = 0; i < DIGITS; i++) {
            nextNumber *= 10;
            nextNumber += nextNumbers[i];
        }


        currentResult += nextNumber;
        return nextNumber;
    }

    @Override
    public int getCurrentResult() {
        return currentResult;
    }

}
