package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/19.
 */

public class OrderFragment extends BaseListFragment<OrderContract.Presenter> implements OrderContract.View {
    @Override
    protected int provideLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected OrderContract.Presenter getPresenter() {
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
