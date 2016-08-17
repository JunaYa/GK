package gank.sin.me.gk.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.ui.adapter.GankAdapter;

/**
 * Created by sin on 2016/8/15.
 */

public class BoonViewModel extends BaseObservable {

    Provider<LinearLayoutManager> mLinearProvider;
    GankAdapter mGankAdapter;

    private boolean mIsEmpty;

    @Inject
    public BoonViewModel(Provider<LinearLayoutManager> linearProvider, GankAdapter gankAdapter) {
        mLinearProvider = linearProvider;
        mGankAdapter = gankAdapter;
    }

    public void setGanks(@NonNull List<Gank> list) {
        mIsEmpty = list.size() == 0 ? true : false;
    }

    @Bindable
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearProvider.get();
    }

    @Bindable
    public boolean isEmpty() {
        return mIsEmpty;
    }
}
