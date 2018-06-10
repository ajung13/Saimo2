package ajung13.github.saimo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hyunah on 2018-06-03.
 */

public class AddMemoDialog extends Dialog {
    private EditText et;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_newmemo_dialog);

        et = (EditText)findViewById(R.id.newMemoEditText);
        addButton = (Button)findViewById(R.id.newMemoAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public AddMemoDialog(Context context){
        super(context);
        Log.e("myLog_dialog", "constructor");
    }
}