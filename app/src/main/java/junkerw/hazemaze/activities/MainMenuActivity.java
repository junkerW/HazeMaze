package junkerw.hazemaze.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import junkerw.hazemaze.R;

public class MainMenuActivity extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
        Button butt_singleGame = findViewById(R.id.SingleGame);
        Button butt_info       = findViewById(R.id.Butt_Info);
        mContentView = findViewById(R.id.Layout_MainMenu);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        butt_singleGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singleGameConfig = new Intent(MainMenuActivity.this, SingleGameSettingActivity.class);
                startActivity(singleGameConfig);
            }
        });
        butt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoWindow = new Intent(MainMenuActivity.this, InfoActivity.class);
                startActivity(infoWindow);
            }
        });
    }

}
