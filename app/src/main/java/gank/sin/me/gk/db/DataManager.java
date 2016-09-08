package gank.sin.me.gk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import com.squareup.sqlbrite.SqlBrite;
import com.squareup.sqlbrite.SqlBrite.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.db.rx.ContentObservable;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;

/**
 * Created by aya on 2016/9/6.
 */
public class DataManager {
    private BriteDatabase db;

    @Inject
    public DataManager(@Named("brite_database") BriteDatabase db) {
        this.db = db;
    }

    /**
     * 补充一下， ContentObservable 和 ObSubscribeCursor 在 raandroid 0.24.0 版本时还是有的
     * @param type
     * @param page
     * @return
     */
    public Observable<List<Gank>> getGanks(String type, final int page) {
        return db.createQuery(Db.TABLE,Db.QUERY_GANKS_SQL,new String[]{type,page +""})
                .flatMap(new Func1<Query, Observable<Cursor>>() {
                    @Override
                    public Observable<Cursor> call(Query query) {
                        return ContentObservable.fromCursor(query.run());
                    }
                }).map(new Func1<Cursor, List<Gank>>() {
                    @Override
                    public List<Gank> call(Cursor cursor) {
                        List<Gank> ganks = new ArrayList<Gank>();
                        while (cursor.moveToNext()){
                            ganks.add(parseGank(cursor));
                        }
                        cursor.close();
                        return ganks;
                    }
                });
    }

    public Observable<Gank> saveGanks(final Gank gank){
        return Observable.create(new Observable.OnSubscribe<Gank>() {
            @Override
            public void call(Subscriber<? super Gank> subscriber) {
                long result = db.insert(Db.TABLE,toContentValue(gank));
                if (result >= 0) subscriber.onNext(gank);
                subscriber.onCompleted();
            }
        });
    }

    /**
     *  将 Gank 转换为 ContentValue
     * @param gank
     * @return
     */
    private ContentValues toContentValue(Gank gank) {
        ContentValues values = new ContentValues();
        values.put("_id",gank.get_id());
        values.put("desc",gank.getDesc());
        values.put("publishedAt",gank.getPublishedAt());
        values.put("source",gank.getSource());
        values.put("type",gank.getType());
        values.put("url",gank.getUrl());
        values.put("who",gank.getWho());
        return values;
    }

    @NonNull
    private Gank parseGank(Cursor cursor) {
        String _id = cursor.getString(cursor.getColumnIndex("_id"));
        String _desc = cursor.getString(cursor.getColumnIndex("desc"));
        String _publishedAt = cursor.getString(cursor.getColumnIndex("publishedAt"));
        String _source = cursor.getString(cursor.getColumnIndex("source"));
        String _url = cursor.getString(cursor.getColumnIndex("url"));
        String _type = cursor.getString(cursor.getColumnIndex("type"));
        String _who = cursor.getString(cursor.getColumnIndex("who"));
        Gank gank = new Gank();
        gank.set_id(_id);
        gank.setDesc(_desc);
        gank.setPublishedAt(_publishedAt);
        gank.setSource(_source);
        gank.setType(_type);
        gank.setUrl(_url);
        gank.setWho(_who);
        return gank;
    }
}
