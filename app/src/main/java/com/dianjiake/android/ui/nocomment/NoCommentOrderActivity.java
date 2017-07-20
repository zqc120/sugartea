package com.dianjiake.android.ui.nocomment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dianjiake.android.base.BaseListActivity;
import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.ui.common.OrderView;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/20.
 */

public class NoCommentOrderActivity extends BaseListActivity implements OrderView {


    @Override
    public void setShowNoCommentHolder(boolean show) {

    }

    @Override
    public boolean getShowNoCommentHolder() {
        return false;
    }

    @Override
    public void setNoCommentCount(int count) {

    }

    @Override
    public int provideContentView() {
        return 0;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public BaseListPresenter getPresenter() {
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
