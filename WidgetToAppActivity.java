package ajung13.github.saimo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

public class WidgetToAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_to_app);

        AddMemoDialog memoDialog = new AddMemoDialog(this);
        memoDialog.setCanceledOnTouchOutside(false);
        memoDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                finish();
            }
        });
        memoDialog.show();
    }
}
