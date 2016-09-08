package gank.sin.me.gk.db;

import android.database.Cursor;

/**
 * Created by aya on 2016/9/6.
 */
public class Db {
    public static final String TABLE = "Gank";
    public static final int VERSION = 1;
    public static  final int BOOLEAN_FALSE = 0;
    public static final int BOOLEAN_TRUE  =1;
    public static final String QUERY_GANKS_SQL = "select * from Gank where type = ? order by id asc limit ? ,20";

    public static String getString(Cursor cursor,String columName){
        return cursor.getString(cursor.getColumnIndexOrThrow(columName));
    }

    public static int getInt(Cursor cursor, String columName){
        return cursor.getInt(cursor.getColumnIndexOrThrow(columName));
    }

    public static boolean getBoolean(Cursor cursor, String columName){
        return getInt(cursor,columName) == BOOLEAN_TRUE;
    }

    private Db(){
       throw new AssertionError("No instances.");
    }
}
