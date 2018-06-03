package ajung13.github.saimo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hyunah on 2018-06-03.
 */

public class AddMemoDialog extends Dialog {
    private EditText et;
    private Button addButton;
    private View.OnClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.widget_newmemo_dialog);

        et = (EditText)findViewById(R.id.newMemoEditText);
        addButton = (Button)findViewById(R.id.newMemoAddButton);

        if(mClickListener != null)
            addButton.setOnClickListener(mClickListener);
    }

    public AddMemoDialog(Context context, String title, View.OnClickListener singleListener){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mClickListener = singleListener;
    }
}
