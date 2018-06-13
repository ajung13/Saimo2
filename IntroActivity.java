package ajung13.github.saimo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class IntroActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        preferences = getSharedPreferences("ajung13.github.saimo", MODE_PRIVATE);
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
        }, 1000);
    }
}
