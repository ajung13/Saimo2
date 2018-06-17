package ajung13.github.saimo;

/**
 * Created by Hyunah on 2018-06-17.
 */

public class ParentListData {
    private String memoTitle;
    private boolean favorite;

    public ParentListData(String memo, boolean favorite){
        this.memoTitle = memo;
        this.favorite = favorite;
    }

    public String getMemoTitle(){
        return memoTitle;
    }
    public void setMemoTitle(String input){
        this.memoTitle = input;
    }

    public boolean getFavorite(){
        return favorite;
    }
    public void setFavorite(boolean input){
        this.favorite = input;
    }
}
