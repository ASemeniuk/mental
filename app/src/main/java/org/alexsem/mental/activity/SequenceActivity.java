package org.alexsem.mental.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.alexsem.mental.R;
import org.alexsem.mental.sequence.SequenceGenerator;
import org.alexsem.mental.sequence.SequenceGeneratorFactory;
import org.alexsem.mental.sequence.SequenceLogger;
import org.alexsem.mental.sequence.SequenceSoundPlayer;
import org.alexsem.mental.sequence.SequenceTimer;
import org.alexsem.mental.view.CountDownView;

import java.util.Locale;

public class SequenceActivity extends AppCompatActivity {

    public static final String ARG_MODE = "sequence_mode";
    public static final String ARG_DIFFICULTY = "sequence_difficulty";
    public static final String ARG_LIMIT = "sequence_limit";
    public static final String ARG_INTERVAL = "sequence_interval";
    public static final String ARG_REPEATS = "sequence_repeats";


    private View setup;
    private Button start;
    private CountDownView countDown;
    private TextView number;

    private View answerLayout;
    private TextView answer;
    private ColorStateList answerTextColor;
    private Button verify;
    private TextView log;
    private Button next;

    private SequenceTimer timer;
    private SequenceGenerator generator;
    private SequenceLogger logger;
    private SequenceSoundPlayer soundPlayer;

    private int correctAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sequence_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
        }

        setup = findViewById(R.id.sequence_setup);
        TextView mode = (TextView) findViewById(R.id.sequence_mode);
        switch (getIntent().getExtras().getInt(ARG_MODE)) {
            case SequenceGeneratorFactory.MODE_SIMPLE:
                mode.setText(R.string.sequence_mode_simple);
                break;
        }
        mode.setText(String.format(Locale.GERMANY, "%s %d", mode.getText().toString(), getIntent().getExtras().getInt(ARG_LIMIT)));
        TextView difficulty = (TextView) findViewById(R.id.sequence_difficulty);
        switch (getIntent().getExtras().getInt(ARG_DIFFICULTY)) {
            case SequenceGeneratorFactory.DIFF_SINGLE_DIGIT:
                difficulty.setText(R.string.sequence_difficulty_1digit);
                break;
            case SequenceGeneratorFactory.DIFF_DECADES:
                difficulty.setText(R.string.sequence_difficulty_decades);
                break;
            case SequenceGeneratorFactory.DIFF_DOUBLE_DIGIT:
                difficulty.setText(R.string.sequence_difficulty_2digit);
                break;
            case SequenceGeneratorFactory.DIFF_HUNDREDS:
                difficulty.setText(R.string.sequence_difficulty_hundreds);
                break;
            case SequenceGeneratorFactory.DIFF_TRIPLE_DIGIT:
                difficulty.setText(R.string.sequence_difficulty_3digit);
                break;
        }
        TextView interval = (TextView) findViewById(R.id.sequence_interval);
        interval.setText(String.format(Locale.GERMANY, getString(R.string.sequence_interval_format), getIntent().getExtras().getInt(ARG_INTERVAL) / 1000f));
        TextView repeats = (TextView) findViewById(R.id.sequence_repeats);
        repeats.setText(String.format(getString(R.string.sequence_repeats_format), getIntent().getExtras().getInt(ARG_REPEATS)));
        start = (Button) findViewById(R.id.sequence_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setup.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
                countDown.setVisibility(View.VISIBLE);
                countDown.startCountdown();
            }
        });

        countDown = (CountDownView) findViewById(R.id.sequence_countdown);
        countDown.setOnCountDownListener(new CountDownView.OnCountDownListener() {
            @Override
            public void onCountDown(int stepsLeft) {
                if (stepsLeft == 1) {
                    soundPlayer.playServiceSound(SequenceSoundPlayer.SOUND_BEEP);
                } else if (stepsLeft == 0) {
                    countDown.setVisibility(View.GONE);
                    startSequence(getIntent().getIntExtra(ARG_INTERVAL, 1000), getIntent().getIntExtra(ARG_REPEATS, 1));
                }
            }
        });
        number = (TextView) findViewById(R.id.sequence_number);

        answerLayout = findViewById(R.id.sequence_answer_layout);
        answer = (TextView) findViewById(R.id.sequence_answer);
        answerTextColor = answer.getTextColors();
        View.OnClickListener inputListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                switch (button.getId()) {
                    case R.id.sequence_0:
                    case R.id.sequence_1:
                    case R.id.sequence_2:
                    case R.id.sequence_3:
                    case R.id.sequence_4:
                    case R.id.sequence_5:
                    case R.id.sequence_6:
                    case R.id.sequence_7:
                    case R.id.sequence_8:
                    case R.id.sequence_9:
                        if (answer.isEnabled()) {
                            if (answer.getText().toString().equals("0")) {
                                answer.setText(button.getText().toString());
                            } else if (answer.getText().length() < 5) {
                                answer.setText(answer.getText() + button.getText().toString());
                            }
                        }
                        break;
                    case R.id.sequence_delete:
                        if (answer.isEnabled()) {
                            answer.setText(getString(R.string.sequence_0));
                        }
                        break;
                }
            }
        };
        findViewById(R.id.sequence_0).setOnClickListener(inputListener);
        findViewById(R.id.sequence_1).setOnClickListener(inputListener);
        findViewById(R.id.sequence_2).setOnClickListener(inputListener);
        findViewById(R.id.sequence_3).setOnClickListener(inputListener);
        findViewById(R.id.sequence_4).setOnClickListener(inputListener);
        findViewById(R.id.sequence_5).setOnClickListener(inputListener);
        findViewById(R.id.sequence_6).setOnClickListener(inputListener);
        findViewById(R.id.sequence_7).setOnClickListener(inputListener);
        findViewById(R.id.sequence_8).setOnClickListener(inputListener);
        findViewById(R.id.sequence_9).setOnClickListener(inputListener);
        findViewById(R.id.sequence_delete).setOnClickListener(inputListener);

        verify = (Button) findViewById(R.id.sequence_verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer.getText().length() == 0) {
                    return;
                }
                if (answer.getText().toString().equals(String.valueOf(correctAnswer))) { //Success
                    soundPlayer.playServiceSound(SequenceSoundPlayer.SOUND_CORRECT);
                    answer.setTextColor(getResources().getColor(R.color.sequence_verify_success));

                } else { //Failure
                    soundPlayer.playServiceSound(SequenceSoundPlayer.SOUND_INCORRECT);
                    answer.setTextColor(getResources().getColor(R.color.sequence_verify_failed));
                }
                answer.setEnabled(false);
                verify.setVisibility(View.GONE);
                log.setText(logger.print());
                log.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

        log = (TextView) findViewById(R.id.sequence_log);
        next = (Button) findViewById(R.id.sequence_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setVisibility(View.GONE);
                answerLayout.setVisibility(View.GONE);
                verify.setVisibility(View.VISIBLE);
                log.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                setup.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
            }
        });

        generator = SequenceGeneratorFactory.newGenerator(
                getIntent().getExtras().getInt(ARG_MODE),
                getIntent().getExtras().getInt(ARG_DIFFICULTY),
                getIntent().getExtras().getInt(ARG_LIMIT)
        );
        logger = new SequenceLogger();
        soundPlayer = new SequenceSoundPlayer(this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Start generating sequence
     * @param interval How quickly to change values
     * @param repeats  How many values to show
     */
    private void startSequence(int interval, int repeats) {
        generator.initialize();
        logger.reset();
        number.setText("");
        number.setVisibility(View.VISIBLE);
        timer = new SequenceTimer(interval, repeats) {
            @Override
            public void onTick(int ticksLeft) {
                int sequenceNumber = generator.getNextNumber();
                soundPlayer.playSequenceSound(sequenceNumber);
                number.setTextColor(generateRandomColor());
                number.setText(String.format(Locale.US, "%+d", sequenceNumber));
                logger.log(sequenceNumber);
            }

            @Override
            public void onFinish() {
                int sequenceResult = generator.getCurrentResult();
                logger.logResult(sequenceResult);
                number.setVisibility(View.GONE);
                correctAnswer = sequenceResult;
                answerLayout.setVisibility(View.VISIBLE);
                answer.setText(getString(R.string.sequence_0));
                answer.setTextColor(answerTextColor);
                answer.setEnabled(true);
                answer.setVisibility(View.VISIBLE);
            }
        };
        timer.start();
    }

    /**
     * Generate random fully-saturated color
     * @return New color
     */
    private int generateRandomColor() {
        float[] hsv = {(int) (Math.random() * 360), 1f, (float) (Math.random() / 3) + 0.4f};
        return Color.HSVToColor(hsv);
    }
}

//TODO resizeable edit text