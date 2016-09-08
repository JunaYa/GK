package gank.sin.me.gk.db.rx;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;

import rx.Observable;

/**
 * Created by aya on 2016/9/6.
 */
public final class ContentObservable {
    private ContentObservable() {
        throw new AssertionError("No instances");
    }

    public static Observable<Cursor> fromCursor(final Cursor cursor) {
        return Observable.create(new OnSubscribeCursor(cursor));
    }
}
