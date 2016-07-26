package com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhiyicx on 2016/3/31.
 */
public class SpaceItemGridDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemGridDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildPosition(view) + 1;
        if (position == 1 || position == 2) {
            outRect.top = space * 2;
        } else {
            outRect.top = space-3;
        }
        outRect.bottom = space-3;

        if (position % 2 == 0) {
            outRect.left = space;
            outRect.right = space * 2;
        } else {
            outRect.left = space * 2;
            outRect.right = space;
        }
    }

}
