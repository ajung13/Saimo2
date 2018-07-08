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
    private ArrayList<ParentListData> parentList;
    private HashMap<ParentListData, ArrayList<ChildListData>> childList;

    public ExpListHelper(Activity activity, int listViewId){
        this.activity = activity;
        this.expandableListView = (ExpandableListView)this.activity.findViewById(listViewId);
        this.parentList = new ArrayList<>();
        this.childList = new HashMap<>();
    }

    public int addParent(String element, boolean favorite){
        //input : the attributes of new parent
        //output : the ordering of the parent
        parentList.add(new ParentListData(element, favorite));
        return parentList.size()-1;
    }
    public void addParents(String[] elements, boolean[] favorites){
        if(elements == null || favorites == null){
            Log.e(TAG, "input is null");
            return;
        }
        if(elements.length != favorites.length){
            Log.e(TAG, "input length is wrong : " + elements.length + ", " + favorites.length);
            return;
        }
        for(int i = 0; i < elements.length; i++)
            parentList.add(new ParentListData(elements[i], favorites[i]));
    }
    public void cleanParents(){
        parentList.clear();
    }

    public void addChildren(int parent, String[] child_detail, String[] child_info){
        //input : parent number
        //       the attributes of all children
        if(child_detail.length != child_info.length){
            Log.e(TAG, "the length of child_detail and info is different");
            return;
        }
        if(parent >= parentList.size()){
            Log.e(TAG, "parent idx is wrong");
            return;
        }

        ArrayList<ChildListData> list1 = new ArrayList<>();
        for(int i = 0; i < child_detail.length; i++) {
            ChildListData childListData = new ChildListData(child_detail[i], child_info[i]);
            list1.add(childListData);
        }
        childList.put(parentList.get(parent), list1);
    }

    public void fix(){
        expAdapter = new CustomExpandableListViewAdapter(activity, parentList, childList);
        expandableListView.setAdapter(expAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.e(TAG, "group clicked");
                return false;
            }
        });
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

    public int getParentNum(){
        return parentList.size();
    }
}
