package com.dianjiake.android.view.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.dianjiake.android.common.LifeCycle;


/**
 * Created by lfs on 16/10/25.
 */

public class LoadMoreRecyclerView extends RecyclerView implements LifeCycle {
    private OnLoadMoreListener mLoadMoreListener;
    private final int VISIBLE_THRESHOLD = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private BaseLoadMoreAdapter mAdapter;
    private boolean mIsEnd;//是否全部加载
    private boolean mNeedLoadMore = true;

    private LinearLayoutManager llm;


    public OnScrollListener mScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mIsEnd || !mNeedLoadMore) {
                return;
            }
            totalItemCount = llm.getItemCount();
            lastVisibleItem = llm.findLastVisibleItemPosition();
            if (!loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)&&totalItemCount>VISIBLE_THRESHOLD) {
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                }
                loading = true;
                mAdapter.setIsLoading(loading);
            }
        }
    };


    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        init();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapter = (BaseLoadMoreAdapter) adapter;
    }


    /**
     * 初始化上拉加载
     */
    private void init() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            llm = (LinearLayoutManager) getLayoutManager();
            addOnScrollListener(mScrollListener);
        }
    }

    /**
     * 加载完成
     */
    public void setLoadComplete() {
        loading = false;
        mAdapter.setIsLoading(loading);
        mAdapter.notifyDataSetChanged();
    }

    public void refresh() {
        mIsEnd = false;
        loading = true;
        mAdapter.refresh();
    }

    /**
     * 设置是否有分割线
     *
     * @param have
     */
    public void setHaveDivider(boolean have) {
        if (have) {
//            addItemDecoration(new DividerItemDecoration(getViewContext(), DividerItemDecoration.VERTICAL_LIST, R.drawable.shape_horizontal_divider));
        }
    }

    /**
     * 是否是最后一页
     */
    public void setIsEnd() {
        mIsEnd = true;
        mAdapter.setIsEnd();
        mAdapter.setIsLoading(loading);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 是否需要加载更多功能
     */
    public void setNeedLoadMore(boolean needLoadMore) {
        mNeedLoadMore = needLoadMore;
        mAdapter.setNeedLoadMore(needLoadMore);
    }

    /**
     * 设置加载更多接口
     *
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void destroy() {
        removeOnScrollListener(mScrollListener);
    }


    /**
     * 加载更多接口
     */
    public static interface OnLoadMoreListener {
        void onLoadMore();
    }

}
