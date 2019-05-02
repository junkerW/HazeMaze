package junkerw.hazemaze.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
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

    Event oldEventStraight = new Event(Event.TYPE_WALKING,"init");

    private View mContentView;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Date startTime = new Date();
        setContentView(R.layout.activity_game_window);

//        mControlsView = findViewById(R.id.fullscreen_content_controls);
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

        final TextView lines = findViewById(R.id.textLine1);
        lines.setMovementMethod(new ScrollingMovementMethod());

        int size = this.getIntent().getIntExtra("size",3);

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
                String wall = getResources().getString(R.string.messWall);
                String lastLine = getLastLine(lines);
                if (!lastLine.equals(wall) || event.getMessage() != wall) {
                    setNewLine(lines, event.getMessage());
//                    oldEventStraight = event;
                }
//                if (event.getMessage() != getResources().getString(R.string.messWall) || getLastLine(lines) != event.getMessage()) {
//                    setNewLine(lines, event.getMessage());
//                    oldEventStraight = event;
//                }
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
        game = new Game(size, getResources());

    }

    private void setNewLine(TextView lines, String line) {
        lines.append("\n" + line);
    }

    private String getLastLine(TextView line) {
        String lines[] = line.getText().toString().split("\\r?\\n");
        String lastOne = lines[lines.length-1];
        return lastOne;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    private void disableButtons(){
        this.butt_left.setEnabled(false);
        this.butt_right.setEnabled(false);
        this.butt_straight.setEnabled(false);
    }
}
