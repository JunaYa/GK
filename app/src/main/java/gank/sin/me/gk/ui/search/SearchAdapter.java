package gank.sin.me.gk.ui.search;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.R;
import gank.sin.me.gk.data.model.Search;
import gank.sin.me.gk.databinding.ItemGankBinding;
import gank.sin.me.gk.databinding.ViewFootMoreBinding;
import gank.sin.me.gk.databinding.ViewFootNoMoreBinding;


/**
 * Created by sin on 2016/8/9.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GankHolder> {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_MORE = 1;
    public static final int TYPE_NO_MORE = 2;
    private List<Search> mSearches = new ArrayList<>();
    private Provider<SearchViewItemModel> mProvider;
    private boolean isMore = false;
    private boolean isNoMore = false;


    @Inject
    public SearchAdapter(Provider<SearchViewItemModel> provider) {
        mProvider = provider;
    }

    public void setGanks(List<Search> searches) {
        mSearches = searches;
        notifyDataSetChanged();
    }

    public void showMore() {
        isMore = true;
        notifyDataSetChanged();
    }

    public void hide() {
        isMore = true ? false : false;
        isNoMore = true ? false : false;
        notifyDataSetChanged();
    }

    public void showNoMore() {
        isNoMore = true;
        notifyDataSetChanged();
    }

    public boolean isLoad() {
        return (isMore | isNoMore) ? true : false;
    }


    /*
     注意，每个布局都会有一个对应bingding生成
     */
    @Override
    public GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DEFAULT) {
            ItemGankBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gank, parent, false);
            return new GankHolder(binding);
        } else if (viewType == TYPE_MORE) {
            ViewFootMoreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_foot_more, parent, false);
            return new GankHolder(binding);
        } else {
            ViewFootNoMoreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_foot_no_more, parent, false);
            return new GankHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(GankHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MORE) {
            return;
        } else if (getItemViewType(position) == TYPE_NO_MORE) {
            return;
        } else {
            SearchViewItemModel model = mProvider.get();
            model.setSearch(mSearches.get(position));
            ((ItemGankBinding)holder.getBinding()).setViewModel(model);
            ((ItemGankBinding)holder.getBinding()).executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        if (isMore) return mSearches == null ? 0 : mSearches.size() + 1;
        else if (isNoMore) return mSearches == null ? 0 : mSearches.size() + 1;
        else return mSearches == null ? 0 : mSearches.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position == mSearches.size() && isMore) {
            return TYPE_MORE;
        } else if (position == mSearches.size() && isNoMore) {
            return TYPE_NO_MORE;
        } else {
            return TYPE_DEFAULT;
        }
    }

    public class GankHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private final T mBinding;

        public GankHolder(T binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }
}
