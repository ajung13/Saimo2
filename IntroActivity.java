package ajung13.github.saimo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        preferences = getSharedPreferences("ajung13.github.saimo", MODE_PRIVATE);
        setAnimations();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(preferences.getBoolean("firstrun", true)){
            Log.e("myLog_intro", "Welcome to saimo! init settings");
            DBManager dbManager = new DBManager();
            dbManager.initDB(this);
            preferences.edit().putBoolean("firstrun", false).apply();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
    }

    private void setAnimations(){
        ImageView iv = findViewById(R.id.intro_img);
        TextView tv1 = findViewById(R.id.intro_txt1);
        TextView tv2 = findViewById(R.id.intro_txt2);

        Animation txtAnim = new AlphaAnimation(0.0f, 1.0f);
        txtAnim.setStartTime(1000);
        txtAnim.setDuration(1000);
        tv1.startAnimation(txtAnim);
        tv2.startAnimation(txtAnim);

        Animation transAnim = new TranslateAnimation(-200, 0, 0, 0);
        transAnim.setDuration(800);
        iv.startAnimation(transAnim);
    }
}
