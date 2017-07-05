package com.dianjiake.android.view.widget;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.dianjiake.android.R;
import com.dianjiake.android.common.LifeCycle;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.UIUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * RecyclerView 上拉加载更多 Adapter 基类
 * Created by Fesen on 2015/12/3.
 */
public abstract class BaseLoadMoreAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements LifeCycle {
    private static int VIEW_PROGRESS = -0x101;
    private static int VIEW_END = -0x102;
    private static int VIEW_NORMAL = -0x003;
    protected List<T> mItems;
    private int mType = VIEW_PROGRESS;
    private RecyclerView.ViewHolder mEndViewHolder;
    private boolean mIsLoading;
    private boolean mIsLoadMore = true;//是否有上拉夹在功能

    private int mLoadAllTextRes;

    List<BaseViewHolder> viewHolders = new ArrayList<>();


    public BaseLoadMoreAdapter(List<T> items) {
        super();
        mItems = items;
    }

    @Deprecated
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;
        if (viewType == VIEW_PROGRESS || viewType == VIEW_END) {
            vh = ProgressViewHolder.newInstance(parent);
        } else {
            vh = myOnCreateViewHolder(parent, viewType);
        }
        viewHolders.add(vh);
        return vh;
    }

    /**
     * 获得该位置下的item
     *
     * @param positon
     */
    public T getItem(int positon) {
        return positon < mItems.size() ? mItems.get(positon) : null;
    }

    public void setItem(int position, T t) {
        if (position < mItems.size()) {
            mItems.set(position, t);
        }
    }

    /**
     * 删除条目，此动作挺危险的，没什么事就别调用了
     */
    public void deleteItem(int position) {
        if (position < mItems.size()) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setNeedLoadMore(boolean needLoadMore) {
        mIsLoadMore = needLoadMore;
    }

    @Override
    public int getItemCount() {
        return mIsLoadMore ? mItems.size() + 1 : mItems.size();
    }

    @Deprecated
    @Override
    public int getItemViewType(int position) {
        if (mIsLoadMore && (getItemCount() == 1 || position == getItemCount() - 1)) {
            return mType;
        } else {
            return myGetItemViewType(position);
        }
    }


    @Deprecated
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != VIEW_PROGRESS && getItemViewType(position) != VIEW_END) {
            myOnBindViewHolder(holder, position);
        } else {
            ((ProgressViewHolder) holder).setItemViewVisible(mType == VIEW_END || mIsLoading);
            ((ProgressViewHolder) holder).setIsEnd(mType == VIEW_END, mItems.size());
            ((ProgressViewHolder) holder).setLoadAllText(mLoadAllTextRes);
        }
    }

    @Override
    public void destroy() {
        if (!CheckEmptyUtil.isEmpty(viewHolders)) {
            for (BaseViewHolder vh : viewHolders) {
                vh.destroy();
            }
        }
        viewHolders.clear();
        viewHolders = null;
    }

    public void setIsEnd() {
        mType = VIEW_END;
    }

    public void refresh() {
        mType = VIEW_PROGRESS;
    }

    /**
     * 是否正在加载
     */
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    /**
     * 全部加载提示文本
     *
     * @param loadAllTextRes
     */
    public void setLoadAllText(@StringRes int loadAllTextRes) {
        mLoadAllTextRes = loadAllTextRes;
    }


    public abstract void myOnBindViewHolder(BaseViewHolder holder, int position);

    public abstract BaseViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType);

    public abstract int myGetItemViewType(int position);


    public static class ProgressViewHolder extends BaseViewHolder {
        View itemView;
        View loadMore;
        View end;
        TextView loadAllTextView;

        public static ProgressViewHolder newInstance(ViewGroup parent) {
            return new ProgressViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_end_and_load_more, parent, false));

        }

        private ProgressViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            loadMore = itemView.findViewById(R.id.footer_end_and_load_more_load);
            end = itemView.findViewById(R.id.footer_end_and_load_more_end);
            loadAllTextView = (TextView) itemView.findViewById(R.id.footer_end_and_load_more_end);
        }

        private void setItemViewVisible(boolean visible) {
            itemView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }

        private void setIsEnd(boolean isEnd, final int size) {
            loadMore.setVisibility(isEnd ? View.GONE : View.VISIBLE);
            end.setVisibility(isEnd ? View.VISIBLE : View.GONE);
            if (isEnd) {
                end.getLayoutParams().height = size > 9 ? UIUtil.getDimensionPixelSize(R.dimen.toolbar_size) : 0;
                end.postInvalidate();
            }
        }

        void setLoadAllText(@StringRes int loadAllTextRes) {
            if (loadAllTextRes != 0) {
                loadAllTextView.setText(loadAllTextRes);
            }
        }

        @Override
        public void destroy() {

        }
    }
}
