package junkerw.hazemaze.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

import junkerw.hazemaze.R;
import junkerw.hazemaze.game.Event;
import junkerw.hazemaze.game.Game;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {

    Button butt_left;
    Button butt_right;
    ImageButton butt_straight;

    private static final int TIME_BEFORE_LIGHT = 1000;
    private static final int LIGHT_TIME         = 2000;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 1;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Date startTime = new Date();
        setContentView(R.layout.activity_game_window);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.textLine1);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        this.butt_left = findViewById(R.id.butt_rotLeft);
        this.butt_right = findViewById(R.id.butt_rotRight);
        this.butt_straight = findViewById(R.id.butt_straight);

//        final TextView[] lines = {findViewById(R.id.textLine1),
//                findViewById(R.id.textLine2),
//                findViewById(R.id.textLine3),
//                findViewById(R.id.textLine4)};
        final TextView lines = findViewById(R.id.textLine1);
        lines.setMovementMethod(new ScrollingMovementMethod());

       game = new Game(3);

        butt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLine(lines, game.inputLeft().getMessage());
            }
        });
        butt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLine(lines, game.inputRight().getMessage());
            }
        });
        butt_straight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = game.inputStraight();
                setNewLine(lines, event.getMessage());
                if (event.getStatus() == Event.TYPE_EXIT){
                    final Date endTime = new Date();
                    disableButtons();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            View whiteLight = findViewById(R.id.whiteLight);
                            whiteLight.setVisibility(View.VISIBLE);
                            //whiteLight.setAnimation(new AlphaAnimation(0,1));
                            Animation fadeIn = new AlphaAnimation(0.0f,1.0f);
                            fadeIn.setDuration(LIGHT_TIME);
                            whiteLight.startAnimation(fadeIn);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent conclusion = new Intent(GameActivity.this,ConclusionActivity.class);
                                    conclusion.putExtra("time",endTime.getTime() - startTime.getTime());
                                    conclusion.putExtra("steps",game.getSteps());
                                    startActivity(conclusion);
                                    finish();
                                }
                            },LIGHT_TIME);
                        }
                    },TIME_BEFORE_LIGHT);

                }
            }
        });

        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    private void setNewLine(TextView[] lines, String line) {
        for (int i = 0; i < lines.length - 1; i++) {
            lines[i].setText(lines[i+1].getText());
        }
        lines[lines.length-1].setText(line);
    }
    private void setNewLine(TextView lines, String line) {

//        lines.setText(lines.getText() + "\n" + line);
        lines.append("\n" + line);
//        lines.append("\n" + ">");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void disableButtons(){
        this.butt_left.setEnabled(false);
        this.butt_right.setEnabled(false);
        this.butt_straight.setEnabled(false);
    }
}
