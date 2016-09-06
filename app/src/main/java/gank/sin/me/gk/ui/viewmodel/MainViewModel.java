package gank.sin.me.gk.ui.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.ui.activity.SearchActivity;

/**
 * Created by sin on 2016/8/8.
 */

public class MainViewModel extends BaseObservable {

    private Provider<Context> mContext;
    private Provider<LinearLayoutManager> mLinearLayoutManager;
    private Provider<StaggeredGridLayoutManager> mGridLayoutManager;

    @Inject
    public MainViewModel(@ActivityContext Provider<Context> context
                          ,Provider<LinearLayoutManager> linearLayoutManagerProvider
                          ,@Named("grid_two") Provider<StaggeredGridLayoutManager> staggeredGridLayoutManagerProvider) {
        mContext = context;
        mLinearLayoutManager = linearLayoutManagerProvider;
        mGridLayoutManager  = staggeredGridLayoutManagerProvider;
    }

    public void onClickFab(View view) {
        mContext.get().startActivity(SearchActivity.newIntent(mContext.get()));
    }

    @Bindable
    public LinearLayoutManager getLinearLayoutManager(){
        return mLinearLayoutManager.get();
    }

    @Bindable
    public StaggeredGridLayoutManager getGridLayoutManager(){
        return mGridLayoutManager.get();
    }
}
