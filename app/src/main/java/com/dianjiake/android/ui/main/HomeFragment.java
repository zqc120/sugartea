package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseListFragment;
import com.dianjiake.android.ui.common.ShopListAdapter;
import com.dianjiake.android.ui.searchlocation.SearchLocationActivity;
import com.dianjiake.android.ui.searchshop.SearchShopActivity;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.dialog.NormalProgressDialog;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.HomeFilterView;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

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
    TextView toolbarSearchInput;
    @BindView(R.id.toolbar_search_holder)
    LinearLayout toolbarSearchHolder;
    @BindView(R.id.toolbar_msg_holder)
    FrameLayout toolbarMsgHolder;
    @BindView(R.id.toolbar)
    LinearLayout toolbarHolder;
    @BindView(R.id.filter)
    HomeFilterView filter;

    NormalProgressDialog pd;

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
        return new ShopListAdapter(presenter.getItems());
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {
        ptrListLayout.getRecyclerView().addOnScrollListener(scrollListener);
        toolbarLocationText.setMaxWidth(UIUtil.getScreenWidth() * 1 / 4);

        toolbarBottomDistance = UIUtil.getStatusBarHeight() + UIUtil.getDimensionPixelSize(R.dimen.toolbar_size);
        adViewTopDistance = UIUtil.getScreenWidth() / 2;
        toolbarHolder.getBackground().mutate().setAlpha(0);
        toolbarSpace.getBackground().mutate().setAlpha(0);
        presenter.addRG(filter.getFilterGroup());
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
    public void setLocationName(String locationName) {
        toolbarLocationText.setText(locationName);
    }

    @Override
    public void judgeScrollY(int totalY) {
        int toolbarThreshold = adViewTopDistance - toolbarBottomDistance;
        if (totalY >= toolbarThreshold) {
            toolbarLocationHolder.setVisibility(View.GONE);
            toolbarMsgHolder.setVisibility(View.GONE);
            toolbarHolder.getBackground().mutate().setAlpha(255);
            toolbarSpace.getBackground().mutate().setAlpha(255);
            filter.setVisibility(View.VISIBLE);
        } else {
            Timber.d("alpha " + (255 * totalY * 1.0f / toolbarThreshold));
            toolbarHolder.getBackground().mutate().setAlpha((int) (255 * totalY * 1.0f / toolbarThreshold));
            toolbarSpace.getBackground().mutate().setAlpha((int) (255 * totalY * 1.0f / toolbarThreshold));
            filter.setVisibility(View.INVISIBLE);
            toolbarLocationHolder.setVisibility(View.VISIBLE);
            toolbarMsgHolder.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showPD() {
        if (pd == null) {
            pd = NormalProgressDialog.newInstance("正在加载，请稍候...");
        }
        pd.showDialog(getFragmentManager(), "pdd");
    }

    @Override
    public void dismissPD() {
        if (pd != null) {
            pd.dismissAllowingStateLoss();
        }
    }

    @OnClick(R.id.toolbar_location_holder)
    void clickLocation(View v) {
        startActivity(IntentUtil.getIntent(SearchLocationActivity.class));
    }

    @OnClick(R.id.toolbar_search_holder)
    void clickSearch(View v) {
        startActivity(IntentUtil.getIntent(SearchShopActivity.class));
    }
}
