package junkerw.hazemaze.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import junkerw.hazemaze.R;

public class SingleGameSettingActivity extends AppCompatActivity {
    private View mContentView;
    TextView seekProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_game_setting);
        mContentView = findViewById(R.id.layout_SingleGameSetting);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Button butt_enter = findViewById(R.id.butt_enter);

        seekProgress = findViewById(R.id.seekBarText);
        final SeekBar sizeSlider = findViewById(R.id.mazeSize_seekBar);


        sizeSlider.setOnSeekBarChangeListener(seekChangeListener);
        butt_enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gameWindow = new Intent(SingleGameSettingActivity.this, GameActivity.class);
                gameWindow.putExtra("size",this.getSize());
                startActivity(gameWindow);
                finish();
            }

            private int getSize() {
                return adjustSeekBar(sizeSlider.getProgress());
            }
        });
    }

    SeekBar.OnSeekBarChangeListener seekChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seekProgress.setText(Integer.toString(adjustSeekBar(progress)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private int adjustSeekBar(int progress) {
        return progress + 3;
    }

}
