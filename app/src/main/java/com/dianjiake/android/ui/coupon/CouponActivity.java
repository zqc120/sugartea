package com.dianjiake.android.ui.coupon;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListActivity;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/25.
 */

public class CouponActivity extends BaseListActivity<CouponContract.Presenter> implements CouponContract.View {
    @Override
    public int provideContentView() {
        return R.layout.activity_list;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public CouponContract.Presenter getPresenter() {
        return null;
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return null;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onReload() {

    }
}
