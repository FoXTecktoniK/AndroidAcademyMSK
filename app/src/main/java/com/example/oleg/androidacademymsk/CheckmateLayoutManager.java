package com.example.oleg.androidacademymsk;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CheckmateLayoutManager extends RecyclerView.LayoutManager {

    private Context mContext;
    private int mCurrentScroll;
    private int leftOffset;

    public CheckmateLayoutManager(Context context) {
        super();

        mCurrentScroll = 0;
        mContext = context;
    }

    private void fillData(final RecyclerView.Recycler recycler, final RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

        initLeftOffset();

        final int left = getPaddingStart();
        final int right = getWidth() - getPaddingEnd();
        int top = getPaddingTop() - mCurrentScroll;

        for (int i = 0, count = state.getItemCount(); i < count; i++) {
            final View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            final int bottom = top + view.getMeasuredHeight();
            final int leftWithOffset = getLeftWithOffset(left, i);
            final int rightWithOffset = getRightWithOffset(right, i);
            layoutDecoratedWithMargins(view, leftWithOffset, top, rightWithOffset, bottom);
            top = bottom;
        }
        recycler.clear();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAndRecycleAllViews(recycler);

        initLeftOffset();

        final int start = getPaddingStart();
        final int end = getWidth() - getPaddingEnd();
        int top = getPaddingTop() - mCurrentScroll;

        for (int i = 0, count = state.getItemCount(); i < count; i++) {
            final View view = recycler.getViewForPosition(i);
            if (view.getParent() == null) {
                addView(view);
                measureChildWithMargins(view, 0, 0);
            }
            final int bottom = top + getDecoratedMeasuredHeight(view);
            final int leftWithOffset = getLeftWithOffset(start, i);
            final int rightWithOffset = getRightWithOffset(end, i);
            layoutDecoratedWithMargins(view, leftWithOffset, top, rightWithOffset, bottom);
            top = bottom;
        }

    }

    private int getRightWithOffset(int right, int i) {
        return right - leftOffset * ((i + 1) % 2);
    }

    private int getLeftWithOffset(int left, int i) {
        return left + leftOffset * (i % 2);
    }

    private void initLeftOffset() {
        leftOffset = getWidth() - getPaddingStart() - getPaddingEnd();
        leftOffset = leftOffset >> 1;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0 || dy == 0) {
            return 0;
        }
        int newScroll = mCurrentScroll + dy;
        newScroll = Math.max(0, newScroll);

        final int diff = newScroll - mCurrentScroll;

        if (diff != 0) {
            mCurrentScroll = newScroll;
            fillData(recycler, state);
        }
        return diff;
    }
}
