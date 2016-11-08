package gank.sin.me.gk.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.db.GankDB;
import gank.sin.me.gk.ui.adapter.GankAdapter;

/**
 * Created by sin on 2016/8/15.
 */

public class BoonViewModel extends BaseObservable {

    private Provider<LinearLayoutManager> mLinearProvider;
    private GankAdapter mGankAdapter;
    private List<Gank> mGanks = new ArrayList<>();
    private boolean mIsEmpty;
    @Inject GankDB mGankDB;


    @Inject
    public BoonViewModel(Provider<LinearLayoutManager> linearProvider, GankAdapter gankAdapter) {
        mLinearProvider = linearProvider;
        mGankAdapter = gankAdapter;
    }

    public void setGanks(final List<Gank> list) {
        mIsEmpty = list != null && list.size() == 0 ? true : false;
        for (Gank gank : list)
            mGankDB.insert(gank);

        final List<Gank> newGank = mGanks;
        newGank.addAll(list);

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mGanks.size();
            }

            @Override
            public int getNewListSize() {
                return newGank.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mGanks.get(oldItemPosition).getPublishedAt().equals(newGank.get(newItemPosition).getPublishedAt());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return mGanks.get(oldItemPosition).getPublishedAt().equals(newGank.get(newItemPosition).getPublishedAt());
            }
        });

        result.dispatchUpdatesTo(mGankAdapter);
        mGanks = newGank;
        mGankAdapter.setGanks(mGanks);
    }

    public void refresh() {
        mGanks.clear();
    }

    @Bindable
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearProvider.get();
    }

    @Bindable
    public boolean isEmpty() {
        return mIsEmpty;
    }

    public GankAdapter getAdapter() {
        return mGankAdapter;
    }
}
