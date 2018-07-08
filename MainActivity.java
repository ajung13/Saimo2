package ajung13.github.saimo;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog_main";
    private ExpListHelper expHelper;
    private boolean resumeFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expHelper = new ExpListHelper(this, R.id.mainExpandableList);
    }

    @Override
    protected void onResume(){
        super.onResume();

        DBtoList();

        if(expHelper.getParentNum() > 0) {
            expHelper.fix();
            resumeFlag = false;
        }
        else if(!resumeFlag){
            FrameLayout layout = (FrameLayout) findViewById(R.id.mainLayout);
            TextView tv = new TextView(this);
            tv.setText("아직 메모가 없어요! 새로 작성해볼까요?");
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(getResources().getColor(R.color.customNavy));
            layout.addView(tv);
            resumeFlag = true;
        }
    }

    private void DBtoList(){
        expHelper.cleanParents();
        DBManager dbManager = new DBManager();
        expHelper.addParents(dbManager.selectAllMemoTitle(this), dbManager.selectAllMemoFavorite(this));
    }

    public void mainNewMemoClicked(View v){
        AddMemoDialog memoDialog = new AddMemoDialog(this);
        memoDialog.setCanceledOnTouchOutside(false);
        memoDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                onResume();
            }
        });
        memoDialog.show();
    }
}
