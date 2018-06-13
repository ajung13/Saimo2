package ajung13.github.saimo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hyunah on 2018-06-03.
 * In DB, there's only one table now: 'Memos'
 * Memos :  idx         INTEGER     AUTOINCREMENT,
 *          favorite    INTEGER     DEFAULT 0,
 *          category    VARCHAR(10) DEFAULT 'NONE',
 *          memo        text,
 *          writeTime   datetime,
 *          PRIMARY KEY(idx)
 **/

public class DBManager {
    static private SQLiteDatabase DB;
    final private String db_name = "myDB";
    final private String table_name = "Memos";
    final private String TAG = "myLog_DBManager";

    public SQLiteDatabase getDB(){
        return this.DB;
    }
    public boolean initDB(Context context){
        boolean errorFlag = false;
        try {
            DB = context.openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);

            String createSQL = "CREATE TABLE IF NOT EXISTS " + table_name + "(";
            createSQL += "idx integer primary key, ";
            createSQL += "favorite integer default 0, ";
            createSQL += "category varchar(10) default 'none', ";
            createSQL += "memo text, ";
            createSQL += "writeTime datetime);";
            DB.execSQL(createSQL);
        }catch(Exception e){
            errorFlag = true;
            Log.e(TAG + "(1)", e.toString());
        }

        if(DB.isOpen())
            DB.close();
        return errorFlag;
    }

    public boolean insert(Context context, String category, String memo){
        boolean errorFlag = false;
        try {
            DB = context.openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
            String insertSQL = "INSERT INTO " + table_name;
            if(category != null) {
                insertSQL += "(category, memo, writeTime) VALUES (";
                insertSQL += "'" + category + "', ";
            }else
                insertSQL += "(memo, writeTime) VALUES (";
            insertSQL += "'" + memo + "', ";
            insertSQL += "'" + getNowTime() + "');";
            DB.execSQL(insertSQL);
        }catch(Exception e){
            errorFlag = true;
            Log.e(TAG + "(2)", e.toString());
        }

        if(DB.isOpen())
            DB.close();
        return errorFlag;
    }

    public String selectAll(Context context){
        String result = "";
        try{
            DB = context.openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);

            Cursor c = DB.rawQuery("SELECT * FROM " + table_name, null);
            if(c!=null){
                result += c.getCount() + " items\n\n";
                if(c.moveToFirst()){
                    result += "idx\tcategory\tfavorite\tWriteTime\tmemo\n";
                    do{
                        result += c.getInt(c.getColumnIndexOrThrow("idx"));
                        result += "\t" + c.getString(c.getColumnIndexOrThrow("category"));
                        result += "\t" + c.getInt(c.getColumnIndexOrThrow("favorite"));
                        result += "\t" + c.getString(c.getColumnIndexOrThrow("writeTime"));
                        result += "\t" + c.getString(c.getColumnIndexOrThrow("memo"));
                        result += "\n";
                    }while(c.moveToNext());
                }
            }
            if(c != null && !c.isClosed())
                c.close();
        }catch(Exception e){
            Log.e(TAG + "(3)", e.toString());
        }

        if(DB.isOpen())
            DB.close();
        return result;
    }

    private String getNowTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
