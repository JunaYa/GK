package gank.sin.me.gk.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.ui.base.BaseActivity;
import gank.sin.me.gk.ui.base.BaseFragment;
import gank.sin.me.gk.ui.fragments.boon.BoonFragment;
import gank.sin.me.gk.ui.fragments.empty.EmptyFragment;

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

    @Provides
    @Named("fragments")
    ArrayList<BaseFragment> provideFragments(){
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new BoonFragment());
        fragments.add(new EmptyFragment());
        return fragments;
    }


}
