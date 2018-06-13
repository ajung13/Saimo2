package ajung13.github.saimo;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hyunah on 2018-06-03.
 */

public class AddMemoDialog extends Dialog {
    final private String TAG = "myLog_dialog";
    private EditText et;
    private Button addButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_newmemo_dialog);

        et = (EditText)findViewById(R.id.newMemoEditText);
        addButton = (Button)findViewById(R.id.newMemoAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(et.getText() == null){
                    Toast.makeText(context, "취소되었습니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "editText is empty");
                    return;
                }
                DBManager dbManager = new DBManager();
                if(!firstrun())
                    dbManager.initDB(context);
                dbManager.insert(context, null, et.getText().toString());

                dismiss();
            }
        });
    }

    public AddMemoDialog(Context context){
        super(context);
        this.context = context;
        Log.e(TAG, "constructor");
    }

    private boolean firstrun(){
        SharedPreferences preferences = context.getSharedPreferences("ajung13.github.saimo", context.MODE_PRIVATE);
        return preferences.getBoolean("firstrun", true);
    }
}
