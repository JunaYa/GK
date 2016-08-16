package gank.sin.me.gk.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import gank.sin.me.gk.R;

/**
 * Created by sin on 2016/8/10.
 */

public class InsertDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public InsertDecoration(Context context) {
        margin = (int) context.getResources().getDimension(R.dimen._4dp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            if (position == 0) {
                outRect.set(margin, margin, margin, margin);
            }else {
                outRect.set(margin, 0, margin, margin);
            }
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            if (position == 0) {
                outRect.set(margin,margin,margin/2,margin);
            } else if (position == 1) {
                outRect.set(margin/2,margin,margin,margin);
            } else if (position > 1) {
                if (position % 2 == 1) {
                    outRect.set(margin / 2, 0, margin, margin);
                } else if (position % 2 == 0) {
                    outRect.set(margin, 0, margin / 2, margin);
                }
            }
        }
    }
}
