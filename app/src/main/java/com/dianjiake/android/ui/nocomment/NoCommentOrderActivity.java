package com.dianjiake.android.ui.nocomment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListActivity;
import com.dianjiake.android.ui.common.OrderListAdapter;
import com.dianjiake.android.ui.common.OrderView;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.PtrListLayout;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/20.
 */

public class NoCommentOrderActivity extends BaseListActivity<NoCommentOrderPresenter> implements OrderView {


    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_icon_left)
    ImageView toolbarIconLeft;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_icon_right)
    ImageView toolbarIconRight;
    @BindView(R.id.toolbar_divider)
    ImageView toolbarDivider;
    @BindView(R.id.toolbar_holder)
    ConstraintLayout toolbarHolder;
    @BindView(R.id.ptr_list)
    PtrListLayout ptrList;

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
        return R.layout.activity_no_comment;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        toolbarTitle.setText("待评价订单");
    }

    @Override
    public NoCommentOrderPresenter getPresenter() {
        return new NoCommentOrderPresenter(this);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new OrderListAdapter(presenter.getItems(), presenter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onReload() {

    }

    @OnClick(R.id.toolbar_icon_left)
    void clickBack(View v) {
        finish();
    }
}
