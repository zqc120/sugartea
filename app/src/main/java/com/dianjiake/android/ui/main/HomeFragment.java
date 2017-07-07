package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/7.
 */

public class HomeFragment extends BaseListFragment {
    @Override
    protected int provideLayout() {
        return 0;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return null;
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onReload() {

    }
}
