package com.dianjiake.android.ui.evaluate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListActivity;
import com.dianjiake.android.common.AndroidBug5497Workaround;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.PtrListLayout;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;

/**
 * Created by lfs on 2017/7/20.
 */

public class EvaluateActivity extends BaseListActivity<EvaluateContract.Presenter> implements EvaluateContract.View {
    public static Intent getStartIntent(OrderBean order) {
        Intent intent = IntentUtil.getIntent(EvaluateActivity.class);
        intent.putExtra("order", order);
        return intent;
    }

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
    public int provideContentView() {
        return R.layout.activity_no_comment;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(this);
        ptrListLayout.setNeedPtr(false);
        ptrListLayout.setNeedLoadMore(false);
        presenter.setServices((OrderBean) getIntent().getParcelableExtra("order"));
    }

    @Override
    public EvaluateContract.Presenter getPresenter() {
        return new EvaluatePresenter(this);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new EvaluateAdapter(presenter.getItems(), presenter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onReload() {

    }

}
