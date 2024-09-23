package com.example.xqtest.scrollabletable;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * User: Lee
 * Date: 2019-07-03
 * Time: 10:50
 *
 * 解决 java.lang.IndexOutOfBoundsException: Inconsistency detected 的问题
 *
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/590
 */
public class CommonLinearLayoutManager extends LinearLayoutManager {

    public CommonLinearLayoutManager(final Context context) {
        super(context);
    }

    public CommonLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CommonLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(final RecyclerView.Recycler recycler, final RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            return super.scrollVerticallyBy(dy, recycler, state);
        } catch (Exception e) {
            return 0;
        }
    }
}
