package gank.sin.me.gk.dagger.module;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.dagger.ApplicationContext;
import gank.sin.me.gk.db.DataManager;
import gank.sin.me.gk.db.OpenHelper;
import rx.schedulers.Schedulers;

/**
 * Created by sin on 2016/8/19.
 */

@Module
public class DBModule {
    @Provides
    public OpenHelper provideOpenHelper(@ApplicationContext Context context){
        return new OpenHelper(context);
    }

    @Provides
    public SqlBrite provideSqlBrite(OpenHelper openHelper){
        return SqlBrite.create();
    }

    @Provides
    public BriteDatabase provideDatabase(SqlBrite sqlBrite, OpenHelper openHelper){
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
        return db;
    }
    @Provides
    public DataManager provideDataManager( BriteDatabase briteDatabase){
        return new DataManager(briteDatabase);
    }
}
