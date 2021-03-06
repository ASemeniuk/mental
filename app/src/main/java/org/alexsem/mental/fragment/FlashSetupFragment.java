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
import org.alexsem.mental.activity.FlashDisplayActivity;

import java.util.Locale;

/**
 * Setup sequence parameters
 */
public class FlashSetupFragment extends Fragment {

    private Spinner difficulty;
    private SeekBar limit;
    private TextView limitText;
    private SeekBar interval;
    private TextView intervalText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flash_setup_fragment, container, false);

        difficulty = (Spinner) view.findViewById(R.id.flash_setup_difficulty);

        //Limit
        limitText = (TextView) view.findViewById(R.id.flash_setup_limit_text);
        limit = (SeekBar) view.findViewById(R.id.flash_setup_limit);
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
        intervalText = (TextView) view.findViewById(R.id.flash_setup_interval_text);
        interval = (SeekBar) view.findViewById(R.id.flash_setup_interval);
        interval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //TODO not called if 0 selected
                intervalText.setText(String.format(Locale.GERMANY, "%1.1f", calculateIntervalValue(progress) / 1000f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        view.findViewById(R.id.flash_setup_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int argDifficulty = difficulty.getSelectedItemPosition() + 1;
                int argLimit = calculateLimitValue(limit.getProgress());
                int argInterval = calculateIntervalValue(interval.getProgress());

                SharedPreferences.Editor prefs = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE).edit();
                prefs.putInt(FlashDisplayActivity.ARG_DIFFICULTY, difficulty.getSelectedItemPosition());
                prefs.putInt(FlashDisplayActivity.ARG_LIMIT, limit.getProgress());
                prefs.putInt(FlashDisplayActivity.ARG_INTERVAL, interval.getProgress());
                prefs.apply();

                Intent intent = new Intent(getActivity(), FlashDisplayActivity.class);
                intent.putExtra(FlashDisplayActivity.ARG_DIFFICULTY, argDifficulty);
                intent.putExtra(FlashDisplayActivity.ARG_LIMIT, argLimit);
                intent.putExtra(FlashDisplayActivity.ARG_INTERVAL, argInterval);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] difficultyArray = getResources().getStringArray(R.array.flash_setup_difficulty_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, difficultyArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        //Restore state
        SharedPreferences prefs = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        difficulty.setSelection(prefs.getInt(FlashDisplayActivity.ARG_DIFFICULTY, 0));
        limit.setProgress(prefs.getInt(FlashDisplayActivity.ARG_LIMIT, 8));
        interval.setProgress(prefs.getInt(FlashDisplayActivity.ARG_INTERVAL, 5));
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
}