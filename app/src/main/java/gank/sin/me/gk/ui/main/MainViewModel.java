package gank.sin.me.gk.ui.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.data.remote.GankApi;
import gank.sin.me.gk.ui.search.SearchActivity;

/**
 * Created by sin on 2016/8/8.
 */

public class MainViewModel extends BaseObservable {

    private Provider<Context> mContext;

    @Inject
    public MainViewModel(@ActivityContext Provider<Context> context) {
        mContext = context;
    }

    public void onClickFab(View view) {
        Log.d("dfsafdsafdsaf", "fdsafdsafdsa");
        mContext.get().startActivity(SearchActivity.newIntent(mContext.get()));
    }

}
