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
