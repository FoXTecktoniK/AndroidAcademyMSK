package com.example.oleg.androidacademymsk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

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

        final int start = getPaddingStart();
        final int end = getWidth() - getPaddingEnd();
        final int width = (getWidth() - getPaddingEnd() - getPaddingStart()) >> 3;
        final int height = (getHeight() - getPaddingEnd() - getPaddingTop()) >> 3;
        final int squareSide = Math.min(width, height);
        int top = getPaddingTop() - mCurrentScroll;

        for (int i = 0, count = state.getItemCount(); i < count; i+= 8) {
            int left = start;
            for (int j = 0; j < 8 && i + j < count; j++) {
                final View view = recycler.getViewForPosition(i + j);
                addView(view);
                final ViewGroup.LayoutParams params = view.getLayoutParams();
                params.width = squareSide;
                params.height = squareSide;
                view.setLayoutParams(params);
                measureChildWithMargins(view, 0, 0);
                int thisTop = top + height * (j % 2);
                layoutDecoratedWithMargins(view, left, thisTop, left + squareSide, thisTop + squareSide);
                left += width;
            }
            top += height << 1;
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

        fillData(recycler, state);

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
