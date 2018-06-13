package ajung13.github.saimo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog_main";
    TextView msgOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgOutput = (TextView)findViewById(R.id.mainText1);
    }

    @Override
    protected void onResume(){
        super.onResume();

        DBManager dbManager = new DBManager();
        msgOutput.setText(dbManager.selectAll(this));
    }
}
