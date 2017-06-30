package org.alexsem.mental.sequence;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

import org.alexsem.mental.R;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public SequenceSoundPlayer(Context context) {
        this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        serviceSounds = new SparseIntArray();
        sequenceSounds = new SparseIntArray();
        new Thread(new SoundLoader(context)).start();
    }

    /**
     * Play sound that represents the specified key
     * @param key System sound key constant
     */
    public void playServiceSound(int key) {
        if (lock.readLock().tryLock()) {
            try {
                if (serviceSounds.indexOfKey(key) >= 0) {
                    soundPool.play(serviceSounds.get(key), 1f, 1f, 1, 0, RATE);
                }
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    /**
     * Play sound that represents the specified number
     * @param number Number to vocalize
     */
    public void playSequenceSound(int number) {
        if (lock.readLock().tryLock()) {
            try {
                if (sequenceSounds.indexOfKey(number) >= 0) {
                    soundPool.play(sequenceSounds.get(number), 1f, 1f, 1, 0, RATE);
                }
            } finally {
                lock.readLock().unlock();
            }
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * Class used to load sounds in background
     */
    private class SoundLoader implements Runnable {
        private Context context;

        public SoundLoader(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            AssetManager assets = context.getAssets();
            String pathSequence = context.getString(R.string.sounds_path_sequence);
            String pathService = context.getString(R.string.sounds_path_service);
            lock.writeLock().lock();
            try {
                //Service sounds
                try {
                    serviceSounds.append(SOUND_BEEP, soundPool.load(assets.openFd(String.format(pathService, "beep")), 1));
                    serviceSounds.append(SOUND_CORRECT, soundPool.load(assets.openFd(String.format(pathService, "correct")), 1));
                    serviceSounds.append(SOUND_INCORRECT, soundPool.load(assets.openFd(String.format(pathService, "incorrect")), 1));
                } catch (IOException e) {
                    //Do nothing
                }
                //Sequence sounds
                for (int i = -99; i <= 99; i++) { //TODO other numbers
                    if (i == 0) {
                        continue;
                    }
                    AssetFileDescriptor descriptor;
                    try {
                        descriptor = assets.openFd(String.format(pathSequence, i < 0 ? "minus" : "plus", Math.abs(i)));
                    } catch (IOException e) {
                        continue;
                    }
                    sequenceSounds.append(i, soundPool.load(descriptor, 1));
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
