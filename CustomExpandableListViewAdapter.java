package ajung13.github.saimo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hyunah on 2018-06-13.
 */

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> mParentList;
    private ArrayList<ChildListData> mChildList;
    private ChildListViewHolder mChildListViewHolder;
    private HashMap<String, ArrayList<ChildListData>> mChildHashMap;

    //constructor
    public CustomExpandableListViewAdapter(Context context, ArrayList<String> parentList, HashMap<String, ArrayList<ChildListData>> childHashMap) {
        this.context = context;
        this.mParentList = parentList;
        this.mChildHashMap = childHashMap;
    }

    //override for parent list view
    @Override
    public String getGroup(int groupPosition){
        return mParentList.get(groupPosition);
    }
    @Override
    public int getGroupCount(){
        return mParentList.size();
    }
    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater groupInfla = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupInfla.inflate(R.layout.listview_parent, parent, false);
        }
        TextView parentText = (TextView)convertView.findViewById(R.id.parentText);
        parentText.setText(getGroup(groupPosition));
        return convertView;
    }

    //override for child list view
    @Override
    public ChildListData getChild(int groupPosition, int childPosition){
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).get(childPosition);
    }
    @Override
    public int getChildrenCount(int groupPosition){
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).size();
    }
    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        ChildListData childListData = (ChildListData)getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater childInfla = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.listview_child, null);

            mChildListViewHolder = new ChildListViewHolder();
            mChildListViewHolder.memoDetailHoder = (TextView)convertView.findViewById(R.id.childTextDetail);
            mChildListViewHolder.memoInfoHolder = (TextView)convertView.findViewById(R.id.childTextInfo);
            convertView.setTag(mChildListViewHolder);
        }else{
            mChildListViewHolder = (ChildListViewHolder)convertView.getTag();
        }

        mChildListViewHolder.memoDetailHoder.setText(getChild(groupPosition, childPosition).getMemoDetail());
        mChildListViewHolder.memoInfoHolder.setText(getChild(groupPosition, childPosition).getMemoInfo());
        return convertView;
    }
    @Override
    public boolean hasStableIds(){
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }
}
