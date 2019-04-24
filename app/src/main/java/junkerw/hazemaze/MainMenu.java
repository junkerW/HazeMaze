package junkerw.hazemaze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
        Button butt_singleGame = findViewById(R.id.SingleGame);

        butt_singleGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameWindow = new Intent(MainMenu.this,GameWindow.class);
                startActivity(gameWindow);
                finish();
            }
        });
    }
}
