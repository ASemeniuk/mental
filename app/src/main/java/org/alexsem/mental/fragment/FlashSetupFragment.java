package org.alexsem.mental.fragment;

import android.content.Intent;
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
    private SeekBar interval;
    private TextView intervalText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flash_setup_fragment, container, false);

        difficulty = (Spinner) view.findViewById(R.id.flash_setup_difficulty);
        intervalText = (TextView) view.findViewById(R.id.flash_setup_interval_text);
        interval = (SeekBar) view.findViewById(R.id.flash_setup_interval);
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
        view.findViewById(R.id.flash_setup_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int argDifficulty = difficulty.getSelectedItemPosition() + 1;
                int argInterval = calculateIntervalValue(interval.getProgress());
                Intent intent = new Intent(getActivity(), FlashDisplayActivity.class);
                intent.putExtra(FlashDisplayActivity.ARG_DIFFICULTY, argDifficulty);
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
        interval.setProgress(5);
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


//TODO save setup to persistent storage