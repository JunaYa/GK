package gank.sin.me.gk.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import gank.sin.me.gk.R;
import gank.sin.me.gk.dagger.ActivityContext;
import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.databinding.ItemBoonBinding;
import gank.sin.me.gk.databinding.ItemGankBinding;
import gank.sin.me.gk.databinding.ViewFootMoreBinding;
import gank.sin.me.gk.databinding.ViewFootNoMoreBinding;
import gank.sin.me.gk.ui.base.BindingViewHolder;
import gank.sin.me.gk.ui.viewmodel.GankItemViewModel;

/**
 * Created by sin on 2016/8/11.
 */

public class GankAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int TYPE_BOON = 0;  //福利
    private static final int TYPE_DEFUALT = 1;  //其他
    private static final int TYPE_MORE = 2;
    private static final int TYPE_NO_MORE = 3;


    private boolean isMore = false;
    private boolean isNoMore = false;
    private List<Gank> mGanks;
    private Provider<GankItemViewModel> mProviderGank;
    private Context mContext;

    private onImageClick onImageClick;

    public void setOnImageClick(GankAdapter.onImageClick onImageClick) {
        this.onImageClick = onImageClick;
    }

    @Inject
    public GankAdapter(Provider<GankItemViewModel> provider , @ActivityContext Context context) {
        mProviderGank = provider;
        mGanks = Collections.emptyList();
        mContext = context;
    }

    public void setGanks(List<Gank> ganks) {
        mGanks = ganks;
    }

    public boolean isLoad(){
        if (isMore || isNoMore) return true;
        else return false;
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
            final ItemBoonBinding binding = (ItemBoonBinding) holder.getBinding();
            binding.executePendingBindings();
            GankItemViewModel model = mProviderGank.get();
            model.setGank(mGanks.get(position));
            binding.setViewModel(model);
            Uri uri = Uri.parse(model.getUrl()+"?imageView2/0/w/100");
            binding.img.setImageURI(uri);

        } else {
            ItemGankBinding binding = (ItemGankBinding) holder.getBinding();
            GankItemViewModel model = mProviderGank.get();
            model.setGank(mGanks.get(position));
            binding.setViewModel(model);
        }

    }


    @Override
    public int getItemCount() {
        if (isMore || isNoMore) return mGanks == null ? 0 : mGanks.size() + 1;
        else return mGanks.size() ;
    }

    @Override
    public int getItemViewType(int position) {
        if (isMore  && position == mGanks.size()) return TYPE_MORE;
        else if (isNoMore && position == mGanks.size()) return TYPE_NO_MORE;
        else if (mGanks.size() > 0 && mGanks.get(position).getType().equals("福利")) return TYPE_BOON;
        else return TYPE_DEFUALT;

    }

    public interface onImageClick{
        void onClick(TextView textView);
    }
}
