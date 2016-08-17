package gank.sin.me.gk.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.ui.adapter.GankAdapter;


/**
 * Created by sin on 2016/8/9.
 */

public class SearchViewModel extends BaseObservable {
    Provider<LinearLayoutManager> mLinearProvider;
    Provider<StaggeredGridLayoutManager> mGridProvider;
    GankAdapter mAdapter;

    @Inject
    public SearchViewModel(Provider<LinearLayoutManager> linear, @Named("grid_two") Provider<StaggeredGridLayoutManager> grid, GankAdapter gankAdapter) {
        mLinearProvider = linear;
        mGridProvider = grid;
        mAdapter = gankAdapter;
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return mLinearProvider.get();
    }

    @Bindable
    public RecyclerView.LayoutManager getGridLayoutManager() {
        return mGridProvider.get();
    }

    public void setItems(ArrayList<Gank> searches) {
        this.mAdapter.setGanks(searches);
        notifyChange();
    }


}
