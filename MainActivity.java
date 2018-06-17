package ajung13.github.saimo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog_main";
    private ExpListHelper expHelper;

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
        expHelper.fix();
    }

    private void DBtoList(){
        DBManager dbManager = new DBManager();
        expHelper.addParents(dbManager.selectAllMemoTitle(this), dbManager.selectAllMemoFavorite(this));

        expHelper.addChildren(0, new String[]{"apple", "pair"}, new String[]{"apple info", "2019-49-93"});
        expHelper.addChildren(1, new String[]{"appl2e", "pa2ir", "per2simmon"}, new String[]{"apple i2nfo", "2019-249-93", "2013-4-402"});
        expHelper.addChildren(2, new String[]{"app3le", "pa3ir", "persimm3on"}, new String[]{"apple info", "2019-49-93", "2013-4-402"});

    }
}
