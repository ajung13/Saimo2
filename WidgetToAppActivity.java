package ajung13.github.saimo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WidgetToAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_to_app);

        AddMemoDialog memoDialog = new AddMemoDialog(this);
        memoDialog.show();
    }
}