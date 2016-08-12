package gank.sin.me.gk.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.ui.base.BaseActivity;

/**
 * Created by qoo on 16-8-5.
 */
@Module
public class ActivityModule {
    private final WeakReference<BaseActivity> mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = new WeakReference(activity);
    }

    @Provides
    Activity provideActivity() {
        return (Activity)this.mActivity.get();
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return (Context)this.mActivity.get();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(this.mActivity.get());
    }

    @Provides
    @Named("grid_two")
    StaggeredGridLayoutManager provideStaggeredGridLayoutManager(){
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }


}
