package org.alexsem.mental.sequence;


import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Simple timer implementation
 * To use, it needs to be extended in order to implement {@link #onTick(int)} and {@link #onFinish()} methods
 * Both these methods are executed on UI thread.
 * First {@link #onTick(int)} callback is triggered right away upon calling {@link #start()} method
 * Parameter <b>interval</b> shows the frequency of {@link #onTick(int)} callback
 * Parameter <b>repeats</b> shows how many times to trigger the {@link #onTick(int)} before final {@link #onFinish()} is called
 * Once stopped, timer can not be restarted. The new object needs to be created.
 * It is possible to stop timer manually, by calling {@link #stop()} method
 * It is possible to pause timer execution with {@link #pause()} method, and resume it using {@link #resume()} method
 */
public abstract class SequenceTimer {

    private final long interval;
    private long nextTime;
    private long timeLeftTillTick; //Only used when paused
    private int ticksLeft;
    private boolean isStarted;
    private boolean isPaused;

    private Handler mHandler = new TimerHandler(this);

    public SequenceTimer(long interval, int numberOfRepeats) {
        this.interval = interval;
        this.ticksLeft = numberOfRepeats;
        this.isStarted = false;
        this.isPaused = false;
    }

    /**
     * Start the countdown.
     */
    public synchronized final void start() {
        if (ticksLeft == 0) {
            onFinish();
            return;
        }
        onTick(ticksLeft);
        nextTime = SystemClock.uptimeMillis() + interval;
        mHandler.sendMessageAtTime(mHandler.obtainMessage(TimerHandler.MESSAGE), nextTime);
        this.isStarted = true;
        this.isPaused = false;
    }

    /**
     * Cancel the countdown.
     */
    public final void stop() {
        mHandler.removeMessages(TimerHandler.MESSAGE);
        this.ticksLeft = 0;
        this.isStarted = false;
        this.isPaused = false;
    }

    /**
     * Pauses the timer
     */
    public void pause() {
        if (isStarted && !isPaused) {
            mHandler.removeMessages(TimerHandler.MESSAGE);
            timeLeftTillTick = nextTime - SystemClock.uptimeMillis();
            isPaused = true;
        }
    }

    /**
     * Resumes the timer
     */
    public void resume() {
        if (isStarted && isPaused) {
            nextTime = SystemClock.uptimeMillis() + timeLeftTillTick;
            mHandler.sendMessageAtTime(mHandler.obtainMessage(TimerHandler.MESSAGE), nextTime);
            isPaused = false;
        }
    }

    /**
     * Callback fired on regular interval
     */
    public abstract void onTick(int ticksLeft);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();


    private static class TimerHandler extends Handler {
        private static final int MESSAGE = 1;
        private final SequenceTimer timer;

        public TimerHandler(SequenceTimer timer) {
            this.timer = timer;
        }

        @Override
        public void handleMessage(Message msg) {
            synchronized (timer) {
                if (timer.ticksLeft != -1) {
                    timer.ticksLeft--;
                    if (timer.ticksLeft <= 0) {
                        timer.onFinish();
                        timer.isStarted = false;
                        return;
                    }
                }
                timer.onTick(timer.ticksLeft);
                long currentTime = SystemClock.uptimeMillis();
                do {
                    timer.nextTime += timer.interval;
                } while (currentTime > timer.nextTime);
                sendMessageAtTime(obtainMessage(MESSAGE), timer.nextTime);
            }
        }


    }
}