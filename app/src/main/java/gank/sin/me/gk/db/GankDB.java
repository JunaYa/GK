package gank.sin.me.gk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import gank.sin.me.gk.data.model.Gank;

/**
 * Created by sin on 2016/8/19.
 */

public class GankDB {
    private Context mContext;

    public GankDB(Context context) {
        mContext = context;
    }

    public boolean insert(Gank gank){
        BaseSQLiteHelper helper  =  new BaseSQLiteHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("_id",gank.get_id());
            values.put("desc",gank.getDesc());
            values.put("publishedAt",gank.getPublishedAt());
            values.put("source",gank.getSource());
            values.put("type",gank.getType());
            values.put("url",gank.getUrl());
            values.put("who",gank.getWho());
            db.insert("Gank",null,values);
            db.setTransactionSuccessful();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();
            db.close();
            helper.close();
        }

    }

    public List<Gank> query(String type, int page){
        List<Gank> ganks = null;
        BaseSQLiteHelper helper = new BaseSQLiteHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.beginTransaction();
        try {
            ganks = new ArrayList<>();
            Cursor cursor = db.rawQuery("select * from Gank where type = ? order by id asc limit ? ,20",new String[]{type,page +""});
            while (cursor.moveToNext()){
                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                String _desc = cursor.getString(cursor.getColumnIndex("desc"));
                String _publishedAt = cursor.getString(cursor.getColumnIndex("publishedAt"));
                String _source = cursor.getString(cursor.getColumnIndex("source"));
                String _url = cursor.getString(cursor.getColumnIndex("url"));
                String _type = cursor.getString(cursor.getColumnIndex("type"));
                String _who  = cursor.getString(cursor.getColumnIndex("who"));
                Gank gank = new Gank();
                gank.set_id(_id);
                gank.setDesc(_desc);
                gank.setPublishedAt(_publishedAt);
                gank.setSource(_source);
                gank.setType(_type);
                gank.setUrl(_url);
                gank.setWho(_who);
                ganks.add(gank);
            }
            cursor.close();
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
            helper.close();
            return ganks;
        }
    }

    public boolean delete(Gank gank){
        BaseSQLiteHelper helper = new BaseSQLiteHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.beginTransaction();
        try {
            db.delete("Gank","_id = ?" ,new String[]{gank.get_id()});
            db.setTransactionSuccessful();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();
            db.close();
            helper.close();
        }
    }

    public boolean clearGanks(){
        BaseSQLiteHelper helper = new BaseSQLiteHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.beginTransaction();
        try {
            db.delete("Gank",null,null);
            db.setTransactionSuccessful();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();
            db.close();
            helper.close();
        }
    }
}
