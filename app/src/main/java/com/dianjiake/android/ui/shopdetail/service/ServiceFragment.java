package com.dianjiake.android.ui.shopdetail.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.ui.common.ServiceListAdapter;
import com.dianjiake.android.ui.shopdetail.BaseShopContentFragment;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/17.
 */

public class ServiceFragment extends BaseShopContentFragment implements ServiceContract.View {

    @Override
    protected ServicePresenter getPresenter() {
        return new ServicePresenter(this, shopId);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new ServiceListAdapter(presenter.getItems(), false);
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}
