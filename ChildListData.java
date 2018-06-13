package ajung13.github.saimo;

/**
 * Created by Hyunah on 2018-06-13.
 */

public class ChildListData {
    private String memoDetail;
    private String memoInfo;

    public ChildListData(String detail, String info){
        this.memoDetail = detail;
        this.memoInfo = info;
    }

    public String getMemoDetail(){
        return memoDetail;
    }
    public void setMemoDetail(String input){
        this.memoDetail = input;
    }

    public String getMemoInfo(){
        return memoInfo;
    }
    public void setMemoInfo(String input){
        this.memoInfo = input;
    }
}
