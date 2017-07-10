package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/7.
 */

public class HomeFragment extends BaseListFragment<HomeContract.Presenter> implements HomeContract.View {

    @Override
    protected int provideLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeContract.Presenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new HomeAdapter(presenter.getItems());
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLoadMore() {
        presenter.load(false);
    }

    @Override
    public void onReload() {
        presenter.reload();
    }
}
