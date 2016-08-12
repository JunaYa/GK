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

    private Gank mGank;
    private Provider<Context> mContext;
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    @Inject
    public MainViewModel(@ActivityContext Provider<Context> context) {
        mContext = context;
    }

    public void onClickFab(View view) {
        Log.d("dfsafdsafdsaf", "fdsafdsafdsa");
        mContext.get().startActivity(SearchActivity.newIntent(mContext.get()));
    }

    @Bindable
    public String get_id() {
        return _id;
    }

    @Bindable
    public String getCreatedAt() {
        return createdAt;
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    @Bindable
    public String getPublishedAt() {
        return publishedAt;
    }

    @Bindable
    public String getSource() {
        return source;
    }

    @Bindable
    public String getType() {
        return type;
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    @Bindable
    public boolean isUsed() {
        return used;
    }

    @Bindable
    public String getWho() {
        return who;
    }
}
