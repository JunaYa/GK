package gank.sin.me.gk.ui.fragments.boon;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import javax.inject.Inject;

import gank.sin.me.gk.common.DateUtils;
import gank.sin.me.gk.data.model.Gank;

/**
 * Created by sin on 2016/8/11.
 */

public class BoonItemViewModel extends BaseObservable {

    private Gank mGank;

    @Inject
    public BoonItemViewModel() {
    }

    public void setGank(Gank gank) {
        mGank = gank;
        notifyChange();
    }

    @Bindable
    public String getCreatedAt() {
        return mGank.getCreatedAt();
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
    public boolean isUsed() {
        return mGank.isUsed();
    }

    @Bindable
    public String getWho() {
        return mGank.getWho();
    }
}
