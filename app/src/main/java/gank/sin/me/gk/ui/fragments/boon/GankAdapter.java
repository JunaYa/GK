package gank.sin.me.gk.ui.fragments.boon;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.R;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.databinding.ItemBoonBinding;
import gank.sin.me.gk.databinding.ItemGankBinding;
import gank.sin.me.gk.databinding.ViewFootMoreBinding;
import gank.sin.me.gk.databinding.ViewFootNoMoreBinding;
import gank.sin.me.gk.ui.base.BindingViewHolder;

/**
 * Created by sin on 2016/8/11.
 */

public class GankAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int TYPE_BOON = 0;  //福利
    private static final int TYPE_ANDROID = 1;  // Android
    private static final int TYPE_IOS = 2;  // iOS
    private static final int TYPE_WEB = 3; // 前端
    private static final int TYPE_SOURCE = 4;  //拓展资源
    private static final int TYPE_ALL = 5;  //拓展资源

    private static final int TYPE_MORE = 11;
    private static final int TYPE_NO_MORE = 12;


    private boolean isMore = false;
    private boolean isNoMore = false;
    private List<Gank> mGanks;
    private Provider<BoonItemViewModel> mProviderGank;

    @Inject
    public GankAdapter(Provider<BoonItemViewModel> provider) {
        mProviderGank = provider;
        mGanks = Collections.emptyList();
    }

    public void setGanks(List<Gank> ganks) {
        mGanks = ganks;
        notifyDataSetChanged();
    }

    public void onShowMore() {
        isMore = true;
        notifyDataSetChanged();
    }

    public void onShowNoMore() {
        isNoMore = true;
        notifyDataSetChanged();
    }

    public void onHide() {
        isMore = isMore ? false : false;
        isNoMore = isNoMore ? false : false;
        notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        if (viewType == TYPE_MORE) {
            ViewFootMoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_foot_more, parent, false);
            return new BindingViewHolder(binding);
        } else if (viewType == TYPE_NO_MORE) {
            ViewFootNoMoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_foot_no_more, parent, false);
            return new BindingViewHolder(binding);
        } else if (viewType == TYPE_BOON) {
            ItemBoonBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_boon, parent, false);
            return new BindingViewHolder(binding);
        } else if (viewType == TYPE_ANDROID) {
            ItemGankBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_gank, parent, false);
            return new BindingViewHolder(binding);
        } else {
            ItemGankBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_gank, parent, false);
            return new BindingViewHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MORE) return;
        else if (getItemViewType(position) == TYPE_NO_MORE) return;
        else if (getItemViewType(position) == TYPE_BOON) {
            ItemBoonBinding binding = (ItemBoonBinding) holder.getBinding();
            BoonItemViewModel model = mProviderGank.get();
            model.setGank(mGanks.get(position));
            binding.setViewModel(model);
            Uri uri = Uri.parse(model.getUrl());
            binding.img.setImageURI(uri);
        } else if (getItemViewType(position) == TYPE_ANDROID
                || getItemViewType(position) == TYPE_IOS
                || getItemViewType(position) == TYPE_WEB
                || getItemViewType(position) == TYPE_SOURCE
                || getItemViewType(position) == TYPE_ALL) {
            ItemGankBinding binding = (ItemGankBinding) holder.getBinding();
            BoonItemViewModel model = mProviderGank.get();
            model.setGank(mGanks.get(position));
            binding.setViewModel(model);
        }
    }


    @Override
    public int getItemCount() {
        if (isMore || isNoMore) return mGanks == null ? 0 : mGanks.size() + 1;
        else return mGanks.size() > 0 ? mGanks.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isMore  && position == mGanks.size()) return TYPE_MORE;
        else if (isNoMore && position == mGanks.size()) return TYPE_NO_MORE;
        else if (mGanks.size() > 0 && mGanks.get(position).getType().equals("福利")) return TYPE_BOON;
        else if (mGanks.size() > 0 && mGanks.get(position).getType().equals("Android"))
            return TYPE_ANDROID;
        else if (mGanks.size() > 0 && mGanks.get(position).getType().equals("iOS")) return TYPE_IOS;
        else if (mGanks.size() > 0 && mGanks.get(position).getType().equals("拓展资源"))
            return TYPE_SOURCE;
        else return TYPE_WEB;

    }
}
