package gank.sin.me.gk.ui.base;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by aya on 16/10/8.
 */

public class DiffCallback<T> extends DiffUtil.Callback {

    private List<T> mNewData, mOldData;

    private IComparison iComparison;

    public DiffCallback (List<T> oldGanks, List<T> newGanks,IComparison comparison){
        this.mOldData = oldGanks;
        this.mNewData = newGanks;
        this.iComparison = comparison;
    }

    @Override
    public int getOldListSize() {
        return mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

        return iComparison.diffItem(oldItemPosition,newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    public interface IComparison{
        boolean  diffItem(int o,int n);
    }
}
