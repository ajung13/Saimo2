package ajung13.github.saimo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog_main";
    TextView msgOutput;

    private ExpandableListView expandableListView;
    private CustomExpandableListViewAdapter expAdapter;
    private ArrayList<String> parentList;
    private ArrayList<ChildListData> list1;
    private ArrayList<ChildListData> list2;
    private ArrayList<ChildListData> list3;
    private HashMap<String, ArrayList<ChildListData>> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();

        parentList = new ArrayList<String>();
        parentList.add("fruit");
        parentList.add("vegetable");
        parentList.add("etc");

        ChildListData apple = new ChildListData("apple", "apple info");
        ChildListData pair = new ChildListData("pair", "2018-04-12");
        ChildListData persimmon = new ChildListData("persimmon", "2013-40-3");
        list1 = new ArrayList<ChildListData>();
        list1.add(apple);
        list1.add(pair);
        list1.add(persimmon);

        ChildListData apple2 = new ChildListData("apple2", "apple info");
        ChildListData pair2 = new ChildListData("pair2", "2018-04-12");
        ChildListData persimmon2 = new ChildListData("persimm2on", "2013-40-3");
        list2 = new ArrayList<ChildListData>();
        list2.add(apple2);
        list2.add(pair2);
        list2.add(persimmon2);

        ChildListData appl3e = new ChildListData("appl3e", "apple info");
        ChildListData pair3 = new ChildListData("pa3ir", "2018-04-12");
        ChildListData persim3mon = new ChildListData("pers3immon", "2013-40-3");
        list3 = new ArrayList<ChildListData>();
        list3.add(appl3e);
        list3.add(pair3);
        list3.add(persim3mon);

        childList = new HashMap<String, ArrayList<ChildListData>>();
        childList.put(parentList.get(0), list1);
        childList.put(parentList.get(1), list2);
        childList.put(parentList.get(2), list3);

        expandableListView = (ExpandableListView)findViewById(R.id.mainExpandableList);
        expAdapter = new CustomExpandableListViewAdapter(this, parentList, childList);
        expandableListView.setAdapter(expAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }
}
