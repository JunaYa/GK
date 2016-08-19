package gank.sin.me.gk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gank.sin.me.gk.R;

/**
 * Created by sin on 2016/8/19.
 */

public class BaseSQLiteHelper extends SQLiteOpenHelper {

    private Context mContext;

    public BaseSQLiteHelper(Context context) {
        super(context, "Gank.db", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(mContext.getResources().getString(R.string.create_table_gank));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
