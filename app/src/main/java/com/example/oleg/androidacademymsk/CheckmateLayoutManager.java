package com.example.oleg.androidacademymsk;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CheckmateLayoutManager extends RecyclerView.LayoutManager {

    private Context mContext;

    public CheckmateLayoutManager(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAndRecycleAllViews(recycler);

        final int left = getPaddingStart();
        final int right = getWidth() - getPaddingEnd();
        int top = getPaddingTop();

        for (int i = 0, count = state.getItemCount(); i < count; i++) {
            final View view = recycler.getViewForPosition(i);
            if (view.getParent() == null) {
                addView(view);
                measureChildWithMargins(view, 0, 0);
            }
            final int bottom = top + getDecoratedMeasuredHeight(view);
            layoutDecoratedWithMargins(view, left, top, right, bottom);
        }

    }
}
