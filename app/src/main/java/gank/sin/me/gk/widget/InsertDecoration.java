package gank.sin.me.gk.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
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
        outRect.set(0,margin,0,0);
    }
}
