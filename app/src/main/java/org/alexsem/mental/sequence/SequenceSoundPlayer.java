package org.alexsem.mental.sequence;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

import org.alexsem.mental.R;

/**
 * Class used for playing sequence sounds
 */

public class SequenceSoundPlayer {

    public static final int SOUND_BEEP = 0;
    public static final int SOUND_CORRECT = 1;
    public static final int SOUND_INCORRECT = 2;

    private SoundPool soundPool;
    private SparseIntArray serviceSounds;
    private SparseIntArray sequenceSounds;
    private final float RATE = 1f;

    public SequenceSoundPlayer(Context context) {
        this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        //Service sounds
        serviceSounds = new SparseIntArray();
        serviceSounds.append(SOUND_BEEP, soundPool.load(context, R.raw.beep, 1));
        serviceSounds.append(SOUND_CORRECT, soundPool.load(context, R.raw.correct, 1));
        serviceSounds.append(SOUND_INCORRECT, soundPool.load(context, R.raw.incorrect, 1));
        //Sequence sounds
        sequenceSounds = new SparseIntArray();
//        sequenceSounds.append(-99, soundPool.load(context, R.raw.minus99, 1));
//        sequenceSounds.append(-98, soundPool.load(context, R.raw.minus98, 1));
//        sequenceSounds.append(-97, soundPool.load(context, R.raw.minus97, 1));
//        sequenceSounds.append(-96, soundPool.load(context, R.raw.minus96, 1));
//        sequenceSounds.append(-95, soundPool.load(context, R.raw.minus95, 1));
//        sequenceSounds.append(-94, soundPool.load(context, R.raw.minus94, 1));
//        sequenceSounds.append(-93, soundPool.load(context, R.raw.minus93, 1));
//        sequenceSounds.append(-92, soundPool.load(context, R.raw.minus92, 1));
//        sequenceSounds.append(-91, soundPool.load(context, R.raw.minus91, 1));
        sequenceSounds.append(-90, soundPool.load(context, R.raw.minus90, 1));
//        sequenceSounds.append(-89, soundPool.load(context, R.raw.minus89, 1));
//        sequenceSounds.append(-88, soundPool.load(context, R.raw.minus88, 1));
//        sequenceSounds.append(-87, soundPool.load(context, R.raw.minus87, 1));
//        sequenceSounds.append(-86, soundPool.load(context, R.raw.minus86, 1));
//        sequenceSounds.append(-85, soundPool.load(context, R.raw.minus85, 1));
//        sequenceSounds.append(-84, soundPool.load(context, R.raw.minus84, 1));
//        sequenceSounds.append(-83, soundPool.load(context, R.raw.minus83, 1));
//        sequenceSounds.append(-82, soundPool.load(context, R.raw.minus82, 1));
//        sequenceSounds.append(-81, soundPool.load(context, R.raw.minus81, 1));
        sequenceSounds.append(-80, soundPool.load(context, R.raw.minus80, 1));
//        sequenceSounds.append(-79, soundPool.load(context, R.raw.minus79, 1));
//        sequenceSounds.append(-78, soundPool.load(context, R.raw.minus78, 1));
//        sequenceSounds.append(-77, soundPool.load(context, R.raw.minus77, 1));
//        sequenceSounds.append(-76, soundPool.load(context, R.raw.minus76, 1));
//        sequenceSounds.append(-75, soundPool.load(context, R.raw.minus75, 1));
//        sequenceSounds.append(-74, soundPool.load(context, R.raw.minus74, 1));
//        sequenceSounds.append(-73, soundPool.load(context, R.raw.minus73, 1));
//        sequenceSounds.append(-72, soundPool.load(context, R.raw.minus72, 1));
//        sequenceSounds.append(-71, soundPool.load(context, R.raw.minus71, 1));
        sequenceSounds.append(-70, soundPool.load(context, R.raw.minus70, 1));
//        sequenceSounds.append(-69, soundPool.load(context, R.raw.minus69, 1));
//        sequenceSounds.append(-68, soundPool.load(context, R.raw.minus68, 1));
//        sequenceSounds.append(-67, soundPool.load(context, R.raw.minus67, 1));
//        sequenceSounds.append(-66, soundPool.load(context, R.raw.minus66, 1));
//        sequenceSounds.append(-65, soundPool.load(context, R.raw.minus65, 1));
//        sequenceSounds.append(-64, soundPool.load(context, R.raw.minus64, 1));
//        sequenceSounds.append(-63, soundPool.load(context, R.raw.minus63, 1));
//        sequenceSounds.append(-62, soundPool.load(context, R.raw.minus62, 1));
//        sequenceSounds.append(-61, soundPool.load(context, R.raw.minus61, 1));
        sequenceSounds.append(-60, soundPool.load(context, R.raw.minus60, 1));
//        sequenceSounds.append(-59, soundPool.load(context, R.raw.minus59, 1));
//        sequenceSounds.append(-58, soundPool.load(context, R.raw.minus58, 1));
//        sequenceSounds.append(-57, soundPool.load(context, R.raw.minus57, 1));
//        sequenceSounds.append(-56, soundPool.load(context, R.raw.minus56, 1));
//        sequenceSounds.append(-55, soundPool.load(context, R.raw.minus55, 1));
//        sequenceSounds.append(-54, soundPool.load(context, R.raw.minus54, 1));
//        sequenceSounds.append(-53, soundPool.load(context, R.raw.minus53, 1));
//        sequenceSounds.append(-52, soundPool.load(context, R.raw.minus52, 1));
//        sequenceSounds.append(-51, soundPool.load(context, R.raw.minus51, 1));
        sequenceSounds.append(-50, soundPool.load(context, R.raw.minus50, 1));
//        sequenceSounds.append(-49, soundPool.load(context, R.raw.minus49, 1));
//        sequenceSounds.append(-48, soundPool.load(context, R.raw.minus48, 1));
//        sequenceSounds.append(-47, soundPool.load(context, R.raw.minus47, 1));
//        sequenceSounds.append(-46, soundPool.load(context, R.raw.minus46, 1));
//        sequenceSounds.append(-45, soundPool.load(context, R.raw.minus45, 1));
//        sequenceSounds.append(-44, soundPool.load(context, R.raw.minus44, 1));
//        sequenceSounds.append(-43, soundPool.load(context, R.raw.minus43, 1));
//        sequenceSounds.append(-42, soundPool.load(context, R.raw.minus42, 1));
//        sequenceSounds.append(-41, soundPool.load(context, R.raw.minus41, 1));
        sequenceSounds.append(-40, soundPool.load(context, R.raw.minus40, 1));
//        sequenceSounds.append(-39, soundPool.load(context, R.raw.minus39, 1));
//        sequenceSounds.append(-38, soundPool.load(context, R.raw.minus38, 1));
//        sequenceSounds.append(-37, soundPool.load(context, R.raw.minus37, 1));
//        sequenceSounds.append(-36, soundPool.load(context, R.raw.minus36, 1));
//        sequenceSounds.append(-35, soundPool.load(context, R.raw.minus35, 1));
//        sequenceSounds.append(-34, soundPool.load(context, R.raw.minus34, 1));
//        sequenceSounds.append(-33, soundPool.load(context, R.raw.minus33, 1));
//        sequenceSounds.append(-32, soundPool.load(context, R.raw.minus32, 1));
//        sequenceSounds.append(-31, soundPool.load(context, R.raw.minus31, 1));
        sequenceSounds.append(-30, soundPool.load(context, R.raw.minus30, 1));
//        sequenceSounds.append(-29, soundPool.load(context, R.raw.minus29, 1));
//        sequenceSounds.append(-28, soundPool.load(context, R.raw.minus28, 1));
//        sequenceSounds.append(-27, soundPool.load(context, R.raw.minus27, 1));
//        sequenceSounds.append(-26, soundPool.load(context, R.raw.minus26, 1));
//        sequenceSounds.append(-25, soundPool.load(context, R.raw.minus25, 1));
//        sequenceSounds.append(-24, soundPool.load(context, R.raw.minus24, 1));
//        sequenceSounds.append(-23, soundPool.load(context, R.raw.minus23, 1));
//        sequenceSounds.append(-22, soundPool.load(context, R.raw.minus22, 1));
//        sequenceSounds.append(-21, soundPool.load(context, R.raw.minus21, 1));
        sequenceSounds.append(-20, soundPool.load(context, R.raw.minus20, 1));
//        sequenceSounds.append(-19, soundPool.load(context, R.raw.minus19, 1));
//        sequenceSounds.append(-18, soundPool.load(context, R.raw.minus18, 1));
//        sequenceSounds.append(-17, soundPool.load(context, R.raw.minus17, 1));
//        sequenceSounds.append(-16, soundPool.load(context, R.raw.minus16, 1));
//        sequenceSounds.append(-15, soundPool.load(context, R.raw.minus15, 1));
//        sequenceSounds.append(-14, soundPool.load(context, R.raw.minus14, 1));
//        sequenceSounds.append(-13, soundPool.load(context, R.raw.minus13, 1));
//        sequenceSounds.append(-12, soundPool.load(context, R.raw.minus12, 1));
//        sequenceSounds.append(-11, soundPool.load(context, R.raw.minus11, 1));
        sequenceSounds.append(-10, soundPool.load(context, R.raw.minus10, 1));
        sequenceSounds.append(-9, soundPool.load(context, R.raw.minus9, 1));
        sequenceSounds.append(-8, soundPool.load(context, R.raw.minus8, 1));
        sequenceSounds.append(-7, soundPool.load(context, R.raw.minus7, 1));
        sequenceSounds.append(-6, soundPool.load(context, R.raw.minus6, 1));
        sequenceSounds.append(-5, soundPool.load(context, R.raw.minus5, 1));
        sequenceSounds.append(-4, soundPool.load(context, R.raw.minus4, 1));
        sequenceSounds.append(-3, soundPool.load(context, R.raw.minus3, 1));
        sequenceSounds.append(-2, soundPool.load(context, R.raw.minus2, 1));
        sequenceSounds.append(-1, soundPool.load(context, R.raw.minus1, 1));
        sequenceSounds.append(1, soundPool.load(context, R.raw.plus1, 1));
        sequenceSounds.append(2, soundPool.load(context, R.raw.plus2, 1));
        sequenceSounds.append(3, soundPool.load(context, R.raw.plus3, 1));
        sequenceSounds.append(4, soundPool.load(context, R.raw.plus4, 1));
        sequenceSounds.append(5, soundPool.load(context, R.raw.plus5, 1));
        sequenceSounds.append(6, soundPool.load(context, R.raw.plus6, 1));
        sequenceSounds.append(7, soundPool.load(context, R.raw.plus7, 1));
        sequenceSounds.append(8, soundPool.load(context, R.raw.plus8, 1));
        sequenceSounds.append(9, soundPool.load(context, R.raw.plus9, 1));
        sequenceSounds.append(10, soundPool.load(context, R.raw.plus10, 1));
//        sequenceSounds.append(11, soundPool.load(context, R.raw.plus11, 1));
//        sequenceSounds.append(12, soundPool.load(context, R.raw.plus12, 1));
//        sequenceSounds.append(13, soundPool.load(context, R.raw.plus13, 1));
//        sequenceSounds.append(14, soundPool.load(context, R.raw.plus14, 1));
//        sequenceSounds.append(15, soundPool.load(context, R.raw.plus15, 1));
//        sequenceSounds.append(16, soundPool.load(context, R.raw.plus16, 1));
//        sequenceSounds.append(17, soundPool.load(context, R.raw.plus17, 1));
//        sequenceSounds.append(18, soundPool.load(context, R.raw.plus18, 1));
//        sequenceSounds.append(19, soundPool.load(context, R.raw.plus19, 1));
        sequenceSounds.append(20, soundPool.load(context, R.raw.plus20, 1));
//        sequenceSounds.append(21, soundPool.load(context, R.raw.plus21, 1));
//        sequenceSounds.append(22, soundPool.load(context, R.raw.plus22, 1));
//        sequenceSounds.append(23, soundPool.load(context, R.raw.plus23, 1));
//        sequenceSounds.append(24, soundPool.load(context, R.raw.plus24, 1));
//        sequenceSounds.append(25, soundPool.load(context, R.raw.plus25, 1));
//        sequenceSounds.append(26, soundPool.load(context, R.raw.plus26, 1));
//        sequenceSounds.append(27, soundPool.load(context, R.raw.plus27, 1));
//        sequenceSounds.append(28, soundPool.load(context, R.raw.plus28, 1));
//        sequenceSounds.append(29, soundPool.load(context, R.raw.plus29, 1));
        sequenceSounds.append(30, soundPool.load(context, R.raw.plus20, 1));
//        sequenceSounds.append(31, soundPool.load(context, R.raw.plus31, 1));
//        sequenceSounds.append(32, soundPool.load(context, R.raw.plus32, 1));
//        sequenceSounds.append(33, soundPool.load(context, R.raw.plus33, 1));
//        sequenceSounds.append(34, soundPool.load(context, R.raw.plus34, 1));
//        sequenceSounds.append(35, soundPool.load(context, R.raw.plus35, 1));
//        sequenceSounds.append(36, soundPool.load(context, R.raw.plus36, 1));
//        sequenceSounds.append(37, soundPool.load(context, R.raw.plus37, 1));
//        sequenceSounds.append(38, soundPool.load(context, R.raw.plus38, 1));
//        sequenceSounds.append(39, soundPool.load(context, R.raw.plus39, 1));
        sequenceSounds.append(30, soundPool.load(context, R.raw.plus40, 1));
//        sequenceSounds.append(41, soundPool.load(context, R.raw.plus41, 1));
//        sequenceSounds.append(42, soundPool.load(context, R.raw.plus42, 1));
//        sequenceSounds.append(43, soundPool.load(context, R.raw.plus43, 1));
//        sequenceSounds.append(44, soundPool.load(context, R.raw.plus44, 1));
//        sequenceSounds.append(45, soundPool.load(context, R.raw.plus45, 1));
//        sequenceSounds.append(46, soundPool.load(context, R.raw.plus46, 1));
//        sequenceSounds.append(47, soundPool.load(context, R.raw.plus47, 1));
//        sequenceSounds.append(48, soundPool.load(context, R.raw.plus48, 1));
//        sequenceSounds.append(49, soundPool.load(context, R.raw.plus49, 1));
        sequenceSounds.append(50, soundPool.load(context, R.raw.plus50, 1));
//        sequenceSounds.append(51, soundPool.load(context, R.raw.plus51, 1));
//        sequenceSounds.append(52, soundPool.load(context, R.raw.plus52, 1));
//        sequenceSounds.append(53, soundPool.load(context, R.raw.plus53, 1));
//        sequenceSounds.append(54, soundPool.load(context, R.raw.plus54, 1));
//        sequenceSounds.append(55, soundPool.load(context, R.raw.plus55, 1));
//        sequenceSounds.append(56, soundPool.load(context, R.raw.plus56, 1));
//        sequenceSounds.append(57, soundPool.load(context, R.raw.plus57, 1));
//        sequenceSounds.append(58, soundPool.load(context, R.raw.plus58, 1));
//        sequenceSounds.append(59, soundPool.load(context, R.raw.plus59, 1));
        sequenceSounds.append(60, soundPool.load(context, R.raw.plus60, 1));
//        sequenceSounds.append(61, soundPool.load(context, R.raw.plus61, 1));
//        sequenceSounds.append(62, soundPool.load(context, R.raw.plus62, 1));
//        sequenceSounds.append(63, soundPool.load(context, R.raw.plus63, 1));
//        sequenceSounds.append(64, soundPool.load(context, R.raw.plus64, 1));
//        sequenceSounds.append(65, soundPool.load(context, R.raw.plus65, 1));
//        sequenceSounds.append(66, soundPool.load(context, R.raw.plus66, 1));
//        sequenceSounds.append(67, soundPool.load(context, R.raw.plus67, 1));
//        sequenceSounds.append(68, soundPool.load(context, R.raw.plus68, 1));
//        sequenceSounds.append(69, soundPool.load(context, R.raw.plus69, 1));
        sequenceSounds.append(70, soundPool.load(context, R.raw.plus70, 1));
//        sequenceSounds.append(71, soundPool.load(context, R.raw.plus71, 1));
//        sequenceSounds.append(72, soundPool.load(context, R.raw.plus72, 1));
//        sequenceSounds.append(73, soundPool.load(context, R.raw.plus73, 1));
//        sequenceSounds.append(74, soundPool.load(context, R.raw.plus74, 1));
//        sequenceSounds.append(75, soundPool.load(context, R.raw.plus75, 1));
//        sequenceSounds.append(76, soundPool.load(context, R.raw.plus76, 1));
//        sequenceSounds.append(77, soundPool.load(context, R.raw.plus77, 1));
//        sequenceSounds.append(78, soundPool.load(context, R.raw.plus78, 1));
//        sequenceSounds.append(79, soundPool.load(context, R.raw.plus79, 1));
        sequenceSounds.append(80, soundPool.load(context, R.raw.plus80, 1));
//        sequenceSounds.append(81, soundPool.load(context, R.raw.plus81, 1));
//        sequenceSounds.append(82, soundPool.load(context, R.raw.plus82, 1));
//        sequenceSounds.append(83, soundPool.load(context, R.raw.plus83, 1));
//        sequenceSounds.append(84, soundPool.load(context, R.raw.plus84, 1));
//        sequenceSounds.append(85, soundPool.load(context, R.raw.plus85, 1));
//        sequenceSounds.append(86, soundPool.load(context, R.raw.plus86, 1));
//        sequenceSounds.append(87, soundPool.load(context, R.raw.plus87, 1));
//        sequenceSounds.append(88, soundPool.load(context, R.raw.plus88, 1));
//        sequenceSounds.append(89, soundPool.load(context, R.raw.plus89, 1));
        sequenceSounds.append(90, soundPool.load(context, R.raw.plus90, 1));
//        sequenceSounds.append(91, soundPool.load(context, R.raw.plus91, 1));
//        sequenceSounds.append(92, soundPool.load(context, R.raw.plus92, 1));
//        sequenceSounds.append(93, soundPool.load(context, R.raw.plus93, 1));
//        sequenceSounds.append(94, soundPool.load(context, R.raw.plus94, 1));
//        sequenceSounds.append(95, soundPool.load(context, R.raw.plus95, 1));
//        sequenceSounds.append(96, soundPool.load(context, R.raw.plus96, 1));
//        sequenceSounds.append(97, soundPool.load(context, R.raw.plus97, 1));
//        sequenceSounds.append(98, soundPool.load(context, R.raw.plus98, 1));
//        sequenceSounds.append(99, soundPool.load(context, R.raw.plus99, 1));
        //TODO add other numbers
    }

    /**
     * Play sound that represents the specified key
     * @param key System sound key constant
     */
    public void playServiceSound(int key) {
        if (serviceSounds.indexOfKey(key) >= 0) {
            soundPool.play(serviceSounds.get(key), 1f, 1f, 1, 0, RATE);
        }
    }

    /**
     * Play sound that represents the specified number
     * @param number Number to vocalize
     */
    public void playSequenceSound(int number) {
        if (sequenceSounds.indexOfKey(number) >= 0) {
            soundPool.play(sequenceSounds.get(number), 1f, 1f, 1, 0, RATE);
        }
    }
}
