package gank.sin.me.gk.ui.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.common.DateUtils;
import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.ui.activity.WebActivity;

/**
 * Created by sin on 2016/8/11.
 */

public class GankItemViewModel extends BaseObservable {

    private Gank mGank;
    private Provider<Context> mContext;
    private Provider<LinearLayoutManager> layoutManagerProvider;

    @Inject
    public GankItemViewModel(Provider<LinearLayoutManager> provider,@ActivityContext Provider<Context>context) {
        layoutManagerProvider = provider;
        mContext = context;
    }

    public void setGank(Gank gank) {
        mGank = gank;
        notifyChange();
    }


    @Bindable
    public String getDesc() {
        return mGank.getDesc();
    }

    @Bindable
    public String getPublishedAt() {
        return  DateUtils.formatDate(DateUtils.parseDate(mGank.getPublishedAt()));
    }

    @Bindable
    public String getSource() {
        return mGank.getSource();
    }

    @Bindable
    public String getType() {
        return mGank.getType();
    }

    @Bindable
    public String getUrl() {
        return mGank.getUrl();
    }


    @Bindable
    public String getWho() {
        return mGank.getWho();
    }

    @Bindable
    public LinearLayoutManager getLinearLayoutManager(){
        return layoutManagerProvider.get();
    }
    public void onWeb(View view){
        mContext.get().startActivity(WebActivity.newIntent(mContext.get(),mGank.getDesc(),mGank.getUrl()));
    }
}
