package com.dianjiake.android.ui.searchshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/12.
 */

public class ServiceResultFragment extends BaseListFragment<ServiceResultPresenter> implements ServiceResultContract.View {
    @Override
    protected int provideLayout() {
        return R.layout.fragment_comment_list;
    }

    @Override
    protected ServiceResultPresenter getPresenter() {
        return new ServiceResultPresenter(this);
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
