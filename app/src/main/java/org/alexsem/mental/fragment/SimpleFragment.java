package org.alexsem.mental.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.alexsem.mental.R;
import org.alexsem.mental.activity.SequenceActivity;
import org.alexsem.mental.sequence.SequenceGeneratorFactory;

import java.util.Locale;

/**
 * Setup sequence parameters
 */
public class SimpleFragment extends Fragment {

    private Spinner difficulty;
    private SeekBar limit;
    private TextView limitText;
    private SeekBar interval;
    private TextView intervalText;
    private SeekBar repeats;
    private TextView repeatsText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_fragment, container, false);

        //Difficulty
        difficulty = (Spinner) view.findViewById(R.id.simple_difficulty);

        //Limit
        limitText = (TextView) view.findViewById(R.id.simple_limit_text);
        limit = (SeekBar) view.findViewById(R.id.simple_limit);
        limit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //TODO not called if 0 selected
                limitText.setText(String.valueOf(calculateLimitValue(progress)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Interval
        intervalText = (TextView) view.findViewById(R.id.simple_interval_text);
        interval = (SeekBar) view.findViewById(R.id.simple_interval);
        interval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                intervalText.setText(String.format(Locale.GERMANY, "%1.1f", calculateIntervalValue(progress) / 1000f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Repeats
        repeatsText = (TextView) view.findViewById(R.id.simple_repeats_text);
        repeats = (SeekBar) view.findViewById(R.id.simple_repeats);
        repeats.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                repeatsText.setText(String.valueOf(calculateRepeatsValue(progress)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Start
        view.findViewById(R.id.simple_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int argMode = SequenceGeneratorFactory.MODE_SIMPLE;
                int argDifficulty = -1;
                switch (difficulty.getSelectedItemPosition()) {
                    case 0:
                        argDifficulty = SequenceGeneratorFactory.DIFF_SINGLE_DIGIT;
                        break;
                    case 1:
                        argDifficulty = SequenceGeneratorFactory.DIFF_DECADES;
                        break;
                    case 2:
                        argDifficulty = SequenceGeneratorFactory.DIFF_DOUBLE_DIGIT;
                        break;
                    case 3:
                        argDifficulty = SequenceGeneratorFactory.DIFF_HUNDREDS;
                        break;
                    case 4:
                        argDifficulty = SequenceGeneratorFactory.DIFF_TRIPLE_DIGIT;
                        break;
                }
                int argLimit = calculateLimitValue(limit.getProgress());
                int argInterval = calculateIntervalValue(interval.getProgress());
                int argRepeats = calculateRepeatsValue(repeats.getProgress());

                SharedPreferences.Editor prefs = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE).edit();
                prefs.putInt(SequenceActivity.ARG_MODE, argMode);
                prefs.putInt(SequenceActivity.ARG_DIFFICULTY, difficulty.getSelectedItemPosition());
                prefs.putInt(SequenceActivity.ARG_LIMIT, limit.getProgress());
                prefs.putInt(SequenceActivity.ARG_INTERVAL, interval.getProgress());
                prefs.putInt(SequenceActivity.ARG_REPEATS, repeats.getProgress());
                prefs.apply();

                Intent intent = new Intent(getActivity(), SequenceActivity.class);
                intent.putExtra(SequenceActivity.ARG_MODE, argMode);
                intent.putExtra(SequenceActivity.ARG_DIFFICULTY, argDifficulty);
                intent.putExtra(SequenceActivity.ARG_LIMIT, argLimit);
                intent.putExtra(SequenceActivity.ARG_INTERVAL, argInterval);
                intent.putExtra(SequenceActivity.ARG_REPEATS, argRepeats);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] difficultyArray = getResources().getStringArray(R.array.simple_difficulty_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, difficultyArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        //Restore state
        SharedPreferences prefs = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        difficulty.setSelection(prefs.getInt(SequenceActivity.ARG_DIFFICULTY, 0));
        limit.setProgress(prefs.getInt(SequenceActivity.ARG_LIMIT, 8));
        interval.setProgress(prefs.getInt(SequenceActivity.ARG_INTERVAL, 5));
        repeats.setProgress(prefs.getInt(SequenceActivity.ARG_REPEATS, 9));
    }

    /**
     * Process limit SeekBar progress into correct value
     * @param progress Current progress shown by SeekBar
     * @return Calculated limit
     */
    private int calculateLimitValue(int progress) {
        return progress + 1;
    }

    /**
     * Process interval SeekBar progress into correct value
     * @param progress Current progress shown by SeekBar
     * @return Calculated interval in milliseconds
     */
    private int calculateIntervalValue(int progress) {
        return (progress + 5) * 100;
    }

    /**
     * Process repeats SeekBar progress into correct value
     * @param progress Current progress shown by SeekBar
     * @return Calculated number of repeats
     */
    private int calculateRepeatsValue(int progress) {
        return progress + 1;
    }


}

