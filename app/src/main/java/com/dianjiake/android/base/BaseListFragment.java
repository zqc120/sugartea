package com.dianjiake.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.R;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.LoadMoreRecyclerView;
import com.dianjiake.android.view.widget.LoadingLayout;
import com.dianjiake.android.view.widget.PtrListLayout;


/**
 * Created by lfs on 2017/6/7.
 */

public abstract class BaseListFragment<P extends BaseListPresenter> extends BaseFragment<P> implements BaseListView
        , LoadingLayout.OnReloadListener,LoadMoreRecyclerView.OnLoadMoreListener {
    PtrListLayout ptrListLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ptrListLayout = (PtrListLayout) view.findViewById(R.id.ptr_list);
        ptrListLayout.setAdapter(provideAdapter());
        ptrListLayout.setOnReloadListener(this);
        ptrListLayout.setOnLoadMoreListener(this);
        viewCreated(view, savedInstanceState);
        onReload();
    }

    @Override
    public void loading() {
        ptrListLayout.statusLoading();
    }

    @Override
    public void loadComplete() {
        ptrListLayout.statusSuccess();
    }

    @Override
    public void loadAll() {
        ptrListLayout.statusAll();
    }

    @Override
    public void loadEmptyContent() {
        ptrListLayout.statusEmpty();
    }

    @Override
    public void loadNetworkError() {
        ptrListLayout.statusError();
    }

    public void setNeedLoadMore(boolean need) {
        ptrListLayout.setNeedLoadMore(need);
    }

    public void setNeedPtr(boolean need){
        ptrListLayout.setNeedPtr(need);
    }

    @Override
    public void onDestroyView() {
        ptrListLayout.destroy();
        super.onDestroyView();
    }

    protected abstract BaseLoadMoreAdapter provideAdapter();

    protected abstract void viewCreated(View view, @Nullable Bundle savedInstanceState);

}
