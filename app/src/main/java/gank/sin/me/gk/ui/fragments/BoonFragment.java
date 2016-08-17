package gank.sin.me.gk.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gank.sin.me.gk.R;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.data.model.Result;
import gank.sin.me.gk.data.remote.GankApi;
import gank.sin.me.gk.databinding.FragmentBoonBinding;
import gank.sin.me.gk.ui.adapter.GankAdapter;
import gank.sin.me.gk.ui.base.BaseFragment;
import gank.sin.me.gk.ui.viewModel.BoonViewModel;
import gank.sin.me.gk.widget.InsertDecoration;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sin on 2016/8/11.
 */

public class BoonFragment extends BaseFragment {

    private FragmentBoonBinding mBinding;

    private int mPage;
    private String mType;
    private int mLastVisibleItem;
    private List<Gank> mGanks = new ArrayList<>();
    @Inject GankApi mGankApi;
    @Inject GankAdapter mAdapter;
    @Inject BoonViewModel mBoonViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boon, container, false);
        mBinding = FragmentBoonBinding.bind(view);
        mBinding.setViewModel(mBoonViewModel);

        mBinding.refresh.setColorSchemeColors(R.color.colorPrimary);
        mBinding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                getData(mType, mPage);
            }
        });

        mBinding.recycler.setHasFixedSize(true);
        if (mBinding.recycler.getLayoutManager() == null) {
            mBinding.recycler.addItemDecoration(new InsertDecoration(getActivity()));
        }
        mBinding.recycler.setAdapter(mAdapter);
        mBinding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mAdapter.onShowMore();
                    mPage += 1;
                    getData(mType, mPage);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    mLastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager gridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();

                    int[] lastPosition = new int[gridLayoutManager.getSpanCount()];
                    gridLayoutManager.findLastVisibleItemPositions(lastPosition);
                    int max = lastPosition[0];
                    for (int value : lastPosition) {
                        if (max < value) {
                            max = value;
                        }
                    }
                    mLastVisibleItem = max;
                }
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGanks.size() == 0 && !mBinding.refresh.isRefreshing()) {
            mBinding.refresh.post(new Runnable() {
                @Override
                public void run() {
                    mBinding.refresh.setRefreshing(true);
                    getData(mType, mPage);
                }
            });
        }
    }

    public void initRecycler(RecyclerView.LayoutManager layoutManager) {
        mBinding.recycler.setLayoutManager(layoutManager);
        mAdapter.notifyDataSetChanged();
    }

    public void getData(String type, int page) {
        mType = type;
        mPage = page;
        mGankApi.getGank(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Result<List<Gank>>>() {
                    @Override
                    public void call(Result<List<Gank>> listResult) {
                        mBinding.refresh.setRefreshing(false);

                        if (mPage == 1) {
                            mGanks.clear();
                        } else {
                            hide();
                        }
                        if (!listResult.error) {
                            mGanks.addAll(listResult.results);
                            mAdapter.setGanks(mGanks);
                            mBoonViewModel.setGanks(mGanks);
                            if (mPage > 1 && listResult.results.size() == 0) showNoMore();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mBinding.refresh.setRefreshing(false);
                        hide();
                    }
                });
    }

    private void showNoMore() {
        mAdapter.onShowNoMore();
        hide();
    }

    private void hide(){
        mBinding.refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.onHide();
            }
        }, 500);
    }

}
