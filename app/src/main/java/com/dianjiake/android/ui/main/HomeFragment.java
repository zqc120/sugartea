package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.HomeFilterView;
import com.dianjiake.android.view.widget.PtrListLayout;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lfs on 2017/7/7.
 */

public class HomeFragment extends BaseListFragment<HomeContract.Presenter> implements HomeContract.View {

    int totalScrollY;
    int toolbarBottomDistance;
    int adViewTopDistance;

    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_location_text)
    TextView toolbarLocationText;
    @BindView(R.id.toolbar_location_holder)
    LinearLayout toolbarLocationHolder;
    @BindView(R.id.toolbar_search_input)
    EditText toolbarSearchInput;
    @BindView(R.id.toolbar_search_holder)
    LinearLayout toolbarSearchHolder;
    @BindView(R.id.toolbar_msg_holder)
    FrameLayout toolbarMsgHolder;
    @BindView(R.id.toolbar)
    LinearLayout toolbarHolder;
    @BindView(R.id.filter)
    HomeFilterView filter;

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalScrollY += dy;
            judgeScrollY(totalScrollY);
        }
    };


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
        ptrListLayout.getRecyclerView().addOnScrollListener(scrollListener);
        toolbarBottomDistance = UIUtil.getStatusBarHeight() + UIUtil.getDimensionPixelSize(R.dimen.toolbar_size);
        adViewTopDistance = UIUtil.getScreenWidth() / 2;
        toolbarHolder.getBackground().mutate().setAlpha(0);
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
    public void onDestroyView() {
        ptrListLayout.getRecyclerView().removeOnScrollListener(scrollListener);
        super.onDestroyView();
    }

    @Override
    public void judgeScrollY(int totalY) {
        int toolbarThreshold = adViewTopDistance - toolbarBottomDistance;
        if (totalY >= toolbarThreshold) {
            toolbarLocationHolder.setVisibility(View.GONE);
            toolbarMsgHolder.setVisibility(View.GONE);
            toolbarHolder.getBackground().mutate().setAlpha(255);
            filter.setVisibility(View.VISIBLE);
        } else {
            toolbarHolder.getBackground().mutate().setAlpha(255 * (totalY / toolbarThreshold));
            filter.setVisibility(View.GONE);
            toolbarLocationHolder.setVisibility(View.VISIBLE);
            toolbarMsgHolder.setVisibility(View.VISIBLE);
        }
    }


}
