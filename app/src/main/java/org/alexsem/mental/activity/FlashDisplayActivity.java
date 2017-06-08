package org.alexsem.mental.activity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.alexsem.mental.R;
import org.alexsem.mental.sequence.NumberGenerator;
import org.alexsem.mental.sequence.SequenceTimer;
import org.alexsem.mental.view.AbacusView;
import org.alexsem.mental.view.CountDownView;

import java.util.Locale;

public class FlashDisplayActivity extends AppCompatActivity {

    public static final String ARG_DIFFICULTY = "flash_difficulty";
    public static final String ARG_INTERVAL = "flash_interval";

    private View setup;
    private Button start;
    private CountDownView countDown;
    private View abacusHolder;
    private AbacusView abacus;
    private Button pause;
    private Button resume;

    private NumberGenerator generator;
    private SequenceTimer timer;
    private SoundPool soundPlayer;
    private int soundPlayerBeepId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_display_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setup = findViewById(R.id.flash_display_setup);
        TextView difficulty = (TextView) findViewById(R.id.flash_display_difficulty);
        int numberOfDigits = getIntent().getExtras().getInt(ARG_DIFFICULTY);
        switch (numberOfDigits) {
            case 1:
                difficulty.setText(R.string.flash_display_difficulty_1);
                break;
            case 2:
                difficulty.setText(R.string.flash_display_difficulty_2);
                break;
            case 3:
                difficulty.setText(R.string.flash_display_difficulty_3);
                break;
            case 4:
                difficulty.setText(R.string.flash_display_difficulty_4);
                break;
            case 5:
                difficulty.setText(R.string.flash_display_difficulty_5);
                break;
        }
        TextView interval = (TextView) findViewById(R.id.flash_display_interval);
        interval.setText(String.format(Locale.GERMANY, getString(R.string.flash_display_interval_format), getIntent().getExtras().getInt(ARG_INTERVAL) / 1000f));
        start = (Button) findViewById(R.id.flash_display_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setup.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
                countDown.setVisibility(View.VISIBLE);
                countDown.startCountdown();
            }
        });

        countDown = (CountDownView) findViewById(R.id.flash_display_countdown);
        countDown.setOnCountDownListener(new CountDownView.OnCountDownListener() {
            @Override
            public void onCountDown(int stepsLeft) {
                if (stepsLeft == 1) {
                    soundPlayer.play(soundPlayerBeepId, 1f, 1f, 1, 0, 1f);
                } else if (stepsLeft == 0) {
                    countDown.setVisibility(View.GONE);
                    startDisplay(getIntent().getIntExtra(ARG_INTERVAL, 1000));
                }
            }
        });

        abacusHolder = findViewById(R.id.flash_display_abacus_holder);
        abacus = (AbacusView) findViewById(R.id.flash_display_abacus);
        abacus.initialize(numberOfDigits);

        pause = (Button) findViewById(R.id.flash_display_pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.pause();
                }
                pause.setVisibility(View.GONE);
                resume.setVisibility(View.VISIBLE);
            }
        });
        resume = (Button) findViewById(R.id.flash_display_resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.resume();
                }
                resume.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }
        });

        generator = new NumberGenerator(numberOfDigits);
        soundPlayer = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPlayerBeepId = soundPlayer.load(this, R.raw.beep, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            timer.resume();
        }
    }

    /**
     * Start generating and showing random number
     * @param interval How quickly to change values
     */
    private void startDisplay(int interval) {
        abacusHolder.setVisibility(View.VISIBLE);
        timer = new SequenceTimer(interval, -1) {
            @Override
            public void onTick(int ticksLeft) {
                abacus.updateValue(generator.generate());
            }

            @Override
            public void onFinish() {
                abacusHolder.setVisibility(View.GONE);
            }
        };
        timer.start();
    }

}