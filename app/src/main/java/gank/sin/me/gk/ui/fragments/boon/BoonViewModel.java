package gank.sin.me.gk.ui.fragments.boon;

import android.databinding.BaseObservable;

import javax.inject.Inject;

import gank.sin.me.gk.data.model.Gank;

/**
 * Created by sin on 2016/8/11.
 */

public class BoonViewModel extends BaseObservable {

    private Gank mGank;

    @Inject
    public BoonViewModel() {
    }
}
