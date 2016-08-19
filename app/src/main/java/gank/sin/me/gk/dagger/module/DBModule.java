package gank.sin.me.gk.dagger.module;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.dagger.ApplicationContext;
import gank.sin.me.gk.db.GankDB;

/**
 * Created by sin on 2016/8/19.
 */

@Module
public class DBModule {
    @Provides
    @Named("context")
    public Context provideContext(@ApplicationContext Context context){
        return context;
    }

    @Provides
    public GankDB provideGankDB(@Named("context") Context context){
        return new GankDB(context);
    }
}
