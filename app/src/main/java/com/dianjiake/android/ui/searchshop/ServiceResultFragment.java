package com.dianjiake.android.ui.searchshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.ui.common.ServiceListAdapter;
import com.dianjiake.android.ui.shopweb.ShopWebActivity;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/12.
 */

public class ServiceResultFragment extends BaseListFragment<ServiceResultPresenter> implements ServiceResultContract.View, BaseLoadMoreAdapter.OnItemClickListener {
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
        BaseLoadMoreAdapter adapter = new ServiceListAdapter(presenter.getItems(), true,true);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {
        ptrListLayout.setNeedPtr(false);
    }

    @Override
    public void onLoadMore() {
        presenter.load(false);
    }

    @Override
    public void onReload() {
        presenter.reload();
    }

    @Override
    public void onClick(Object t, int position) {
        startActivity(ShopWebActivity.getServiceDetail((ServiceBean) t));
    }
}
