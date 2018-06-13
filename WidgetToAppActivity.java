package ajung13.github.saimo;

import android.app.Activity;
import android.os.Bundle;

public class WidgetToAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_to_app);

        AddMemoDialog memoDialog = new AddMemoDialog(this);
        memoDialog.show();
    }
}
