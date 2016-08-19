package gank.sin.me.gk.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.dagger.ApplicationContext;
import gank.sin.me.gk.db.GankDB;

/**
 * Created by qoo on 16-8-5.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    Resources provideResources(){
        return mApplication.getResources();
    }

}
