package ajung13.github.saimo;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hyunah on 2018-06-17.
 */

public class ExpListHelper {
    private final String TAG = "myLog_ListMan";
    private Activity activity;
    private ExpandableListView expandableListView;
    private CustomExpandableListViewAdapter expAdapter;
    private ArrayList<String> parentList;
    private ArrayList<ChildListData> list2;
    private ArrayList<ChildListData> list3;
    private HashMap<String, ArrayList<ChildListData>> childList;

    public ExpListHelper(Activity activity, int listViewId){
        this.activity = activity;
        this.expandableListView = (ExpandableListView)this.activity.findViewById(listViewId);
        this.parentList = new ArrayList<String>();
        this.childList = new HashMap<String, ArrayList<ChildListData>>();
    }

    public int addParent(String element){
        //input : the attributes of new parent
        //output : the ordering of the parent
        parentList.add(element);
        return parentList.size()-1;
    }

    public void addChildren(int parent, String[] child_detail, String[] child_info){
        //input : parent number
        //       the attributes of all children
        if(child_detail.length != child_info.length){
            Log.e(TAG, "the length of child_detail and info is different");
            return;
        }

        ArrayList<ChildListData> list1 = new ArrayList<ChildListData>();
        for(int i = 0; i < child_detail.length; i++) {
            ChildListData childListData = new ChildListData(child_detail[i], child_info[i]);
            list1.add(childListData);
        }
        childList.put(parentList.get(parent), list1);
    }

    public void fix(){
        expAdapter = new CustomExpandableListViewAdapter(activity, parentList, childList);
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
