package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.ui.common.OrderListAdapter;
import com.dianjiake.android.ui.common.OrderPresenter;
import com.dianjiake.android.ui.common.OrderView;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.PtrListLayout;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lfs on 2017/7/19.
 */

public class OrderFragment extends BaseListFragment<OrderPresenter> implements OrderView {
    boolean showNoComment = true;


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
    @BindView(R.id.no_comment_text)
    TextView noCommentText;
    @BindView(R.id.no_comment_holder)
    View noCommentHolder;

    @Override
    protected int provideLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected OrderPresenter getPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new OrderListAdapter(presenter.getItems(), presenter);
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {
        toolbarIconLeft.setVisibility(View.INVISIBLE);
        toolbarTitle.setText("订单");
    }

    @Override
    public void onLoadMore() {
        presenter.load(false);
    }

    @Override
    public void onReload() {
        presenter.reload();
    }

    @OnClick(R.id.no_comment_close)
    void clickClose(View v) {
        setShowNoCommentHolder(false);
        presenter.removeHeaderItem();
    }

    @OnClick(R.id.no_comment_holder)
    void clickComment(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setShowNoCommentHolder(boolean show) {
        showNoComment = show;
        noCommentHolder.setVisibility(showNoComment ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public boolean getShowNoCommentHolder() {
        return showNoComment;
    }

    @Override
    public void setNoCommentCount(int count) {
        noCommentHolder.setVisibility(showNoComment ? View.VISIBLE : View.INVISIBLE);
        noCommentText.setText(count + "个订单未评价，点击评价");
    }


}
