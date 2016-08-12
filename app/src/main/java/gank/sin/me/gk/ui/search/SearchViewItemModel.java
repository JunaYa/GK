package gank.sin.me.gk.ui.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.common.DateUtils;
import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.data.model.Search;
import gank.sin.me.gk.ui.web.WebActivity;

/**
 * Created by sin on 2016/8/9.
 */

public class SearchViewItemModel extends BaseObservable {

    private Search mSearch;
    private Provider<Context> mContextProvider;

    @Inject
    public SearchViewItemModel(@ActivityContext Provider<Context> contextProvider) {
        mContextProvider = contextProvider;
    }

    public void setSearch(Search search) {
        this.mSearch = search;
        notifyChange();
    }

    @Bindable
    public String getDesc() {
        return mSearch.getDesc();
    }

    @Bindable
    public String getPublishedAt() {
        return DateUtils.formatDate(DateUtils.parseDate(mSearch.getPublishedAt()));
    }

    @Bindable
    public String getReadability() {
        return mSearch.getReadability();
    }

    @Bindable
    public String getType() {
        return mSearch.getType();
    }

    @Bindable
    public String getUrl() {
        return mSearch.getUrl();
    }

    @Bindable
    public String getWho() {
        return mSearch.getWho();
    }

    public void onWebActivity(View view) {
        mContextProvider.get().startActivity(WebActivity.newIntent(mContextProvider.get(), mSearch.getDesc(), mSearch.getUrl()));
    }
}
