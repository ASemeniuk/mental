package org.alexsem.mental.sequence;

/**
 * Used to choose correct {@link SequenceGenerator} class
 */

public abstract class SequenceGeneratorFactory {

    public static final int MODE_SIMPLE = 0;

    public static final int DIFF_SINGLE_DIGIT = 10;
    public static final int DIFF_DECADES = 20;
    public static final int DIFF_DOUBLE_DIGIT = 30; //TODO implement
    public static final int DIFF_HUNDREDS = 40; //TODO implement
    public static final int DIFF_TRIPLE_DIGIT = 50; //TODO implement

    /**
     * Returns appropriate {@link SequenceGenerator} based on the parameters
     * @param mode       Generator mode
     * @param difficulty Size of numbers
     * @param limit      Maximum digit to use
     * @return Appropriate SequenceGenerator
     */
    public static SequenceGenerator newGenerator(int mode, int difficulty, int limit) {
        switch (mode) {
            case MODE_SIMPLE:
                switch (difficulty) {
                    case DIFF_SINGLE_DIGIT:
                        return new Simple1DigitSequenceGenerator(limit);
                    case DIFF_DECADES:
                        return new SimpleDecadeSequenceGenerator(limit);
                    case DIFF_DOUBLE_DIGIT:
                        return new Simple2DigitSequenceGenerator(limit);
                    case DIFF_HUNDREDS:
                        return new SimpleHundredSequenceGenerator(limit);
                }
                break;
            //TODO other modes
        }
        throw new IllegalArgumentException("Incorrect parameters");

    }


    /**
     * Returns appropriate {@link SequenceGenerator} based on the parameters
     * @param mode       Generator mode
     * @param difficulty Size of numbers
     * @return Appropriate SequenceGenerator
     */
    public static SequenceGenerator newGenerator(int mode, int difficulty) {
        return newGenerator(mode, difficulty, 9);
    }


}
