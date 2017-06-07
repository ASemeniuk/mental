package org.alexsem.mental.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import org.alexsem.mental.R;

/**
 * Displays countdown component and performs animations
 */

public class CountDownView extends FrameLayout {

    private View c3;
    private View c2;
    private View c1;

    private Animation a3;
    private Animation a2;
    private Animation a1;

    private OnCountDownListener onCountDownListener;

    public CountDownView(@NonNull Context context) {
        super(context);
        initialize(context);
    }

    public CountDownView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CountDownView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_countdown, this, true);
        c3 = findViewById(R.id.countdown_3);
        c2 = findViewById(R.id.countdown_2);
        c1 = findViewById(R.id.countdown_1);
        a3 = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        a2 = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        a1 = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        a3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                c3.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (onCountDownListener != null) {
                    onCountDownListener.onCountDown(2);
                }
                c3.setVisibility(View.INVISIBLE);
                c2.startAnimation(a2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                c2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (onCountDownListener != null) {
                    onCountDownListener.onCountDown(1);
                }
                c2.setVisibility(View.INVISIBLE);
                c1.startAnimation(a1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                c1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c1.setVisibility(View.INVISIBLE);
                if (onCountDownListener != null) {
                    c1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onCountDownListener.onCountDown(0);
                        }
                    }, 100);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    public void setOnCountDownListener(OnCountDownListener listener) {
        this.onCountDownListener = listener;
    }

    public void startCountdown() {
        c3.setVisibility(INVISIBLE);
        c2.setVisibility(INVISIBLE);
        c1.setVisibility(INVISIBLE);
        c3.startAnimation(a3);
    }

    //----------------------------------------------------------------------------------------------

    public interface OnCountDownListener {
        void onCountDown(int stepsLeft);
    }
}