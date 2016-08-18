package gank.sin.me.gk.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import gank.sin.me.gk.BR;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.ui.adapter.GankAdapter;


/**
 * Created by sin on 2016/8/9.
 */

public class SearchViewModel extends BaseObservable {
    private Provider<LinearLayoutManager> mLinearProvider;
    private Provider<StaggeredGridLayoutManager> mGridProvider;
    private GankAdapter mAdapter;
    private List<Gank> mGanks;
    private boolean isEmpty = false;

    @Inject
    public SearchViewModel(Provider<LinearLayoutManager> linear, @Named("grid_two") Provider<StaggeredGridLayoutManager> grid, GankAdapter gankAdapter) {
        mLinearProvider = linear;
        mGridProvider = grid;
        mAdapter = gankAdapter;
    }

    public void setGanks(List<Gank> ganks) {
        if (ganks != null && ganks.size() == 0) {
            isEmpty = true;
            mAdapter.setGanks(ganks);
            notifyChange();
        } else {
            isEmpty = false;
        }
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return mLinearProvider.get();
    }

    @Bindable
    public RecyclerView.LayoutManager getGridLayoutManager() {
        return mGridProvider.get();
    }

    public void refresh() {
        if (mGanks != null) mGanks.clear();
    }

    public GankAdapter getAdapter() {
        return mAdapter;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
