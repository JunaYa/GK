package gank.sin.me.gk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gank.sin.me.gk.R;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.data.model.Result;
import gank.sin.me.gk.data.remote.GankApi;
import gank.sin.me.gk.databinding.ActivitySearchBinding;
import gank.sin.me.gk.ui.base.BaseActivity;
import gank.sin.me.gk.ui.viewmodel.SearchViewModel;
import gank.sin.me.gk.widget.InsertDecoration;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding mBinding;
    @Inject SearchViewModel mSearchViewModel;
    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject GankApi mGankApi;

    private String mType = "all";
    private int mPage = 1;
    private int lastVisibleItem;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mBinding.setViewModel(mSearchViewModel);
        setSupportActionBar(mBinding.toolbar);
        showBack();
        mBinding.refresh.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimary));
        mBinding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mSearchViewModel.refresh();
                getData(mBinding.search.getText().toString().trim(), mType);
            }
        });
        mBinding.recycler.setHasFixedSize(true);
        if (mBinding.recycler.getLayoutManager() == null) {
            mBinding.recycler.setLayoutManager(mLinearLayoutManager);
            mBinding.recycler.addItemDecoration(new InsertDecoration(this));
        }
        mBinding.recycler.setAdapter(mSearchViewModel.getAdapter());
        mBinding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mSearchViewModel.getAdapter().getItemCount()) {
                    if (mSearchViewModel.getAdapter().isLoad()) {
                        return;
                    } else if (mSearchViewModel.isEmpty()){
                        onShowNoMore();
                    }else {
                        mPage += 1;
                        mSearchViewModel.getAdapter().onShowMore();
                        getData(mBinding.search.getText().toString().trim(), mType);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                if (getCurrentFocus() != null) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                mBinding.refresh.setRefreshing(true);
                getData(mBinding.search.getText().toString().trim(), mType);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(String query, String type) {
        mGankApi.getSearch(query, type, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Result<List<Gank>>>() {
                    @Override
                    public void call(Result<List<Gank>> value) {
                        onLoadFinish();
                        if (!value.error) {
                            if (value.results.size() == 0) {
                                onShowNoMore();
                            }
                            mSearchViewModel.setGanks(value.results);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onLoadFinish();
                    }
                });
    }

    private void onShowNoMore() {
        mSearchViewModel.getAdapter().onShowNoMore();
        mBinding.refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSearchViewModel.getAdapter().onHide();
            }
        }, 500);
    }

    private void onLoadFinish() {
        mBinding.refresh.setRefreshing(false);
        mBinding.refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSearchViewModel.getAdapter().onHide();
            }
        }, 500);
    }

}
