package gank.sin.me.gk.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import javax.inject.Inject;

/**
 * Created by sin on 2016/8/10.
 */

public class WebViewModel extends BaseObservable {

    private String mTitle;
    private String mUrl;

    @Inject
    public WebViewModel() {
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    @Bindable
    public String getUrl() {
        return mUrl;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
