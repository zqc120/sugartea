package com.dianjiake.android.ui.shopdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.FragmentFactory;
import com.dianjiake.android.data.bean.ShopDetailBean;
import com.dianjiake.android.ui.shopdetail.service.ServiceFragment;
import com.dianjiake.android.util.AMapUtil;
import com.dianjiake.android.util.DateUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.util.LongUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.StarView;
import com.dianjiake.android.view.widget.ToolbarSpaceView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/14.
 */

public class ShopDetailActivity extends BaseTranslateActivity<ShopDetailPresenter> implements ShopDetailContract.View {
    private static final String KEY_ID = "keyid";
    @BindView(R.id.shop_logo)
    SimpleDraweeView shopLogo;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.system_toolbar)
    Toolbar systemToolbar;

    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_icon_left)
    ImageView toolbarIconLeft;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_icon_right)
    ImageView toolbarIconRight;
    @BindView(R.id.toolbar_holder)
    ConstraintLayout toolbarHolder;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.star)
    StarView star;
    @BindView(R.id.shop_time)
    TextView shopTime;
    @BindView(R.id.shop_location)
    TextView shopLocation;
    @BindView(R.id.shop_call)
    FrameLayout shopCall;
    @BindView(R.id.shop_cards)
    ViewPager shopCards;

    ShopDetailAdapter adapter;
    List<BaseShopContentFragment> fragmentList = new ArrayList<>();

    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            for (BaseShopContentFragment f : fragmentList) {
                f.setAppbarOffset(verticalOffset);
            }
        }
    };


    public static Intent getStartIntent(String shopId) {
        Intent intent = IntentUtil.getIntent(ShopDetailActivity.class);
        intent.putExtra(KEY_ID, shopId);
        return intent;
    }

    @Override
    public int provideContentView() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        systemToolbar.getLayoutParams().height += UIUtil.getStatusBarHeight();
        fragmentList.clear();
        fragmentList.add(FragmentFactory.createFragment(ServiceFragment.class, ServiceFragment.getBundle(getShopId())));
        fragmentList.add(FragmentFactory.createFragment(ServiceFragment.class, ServiceFragment.getBundle(getShopId())));
        fragmentList.add(FragmentFactory.createFragment(ServiceFragment.class, ServiceFragment.getBundle(getShopId())));
        adapter = new ShopDetailAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tab.setupWithViewPager(viewPager);
        appbar.addOnOffsetChangedListener(onOffsetChangedListener);
    }

    @Override
    public ShopDetailPresenter getPresenter() {
        return new ShopDetailPresenter(this);
    }

    @Override
    public void setPresenter(ShopDetailContract.Presenter presenter) {

    }

    @Override
    public String getShopId() {
        return getIntent().getStringExtra(KEY_ID);
    }

    @Override
    public void setView(ShopDetailBean shopDetail) {
        shopLogo.setImageURI(FrescoUtil.getShopLogoUri(shopDetail.getDianpu().getLogo(), shopDetail.getDianpu().getCover()));
        shopName.setText(shopDetail.getDianpu().getMingcheng());
        shopTime.setText("营业时间：" + DateUtil.formatOnlyHM(shopDetail.getDianpu().getKaishishijian()) + DateUtil.formatOnlyHM(shopDetail.getDianpu().getJieshushijian()));
        shopLocation.setText(shopDetail.getDianpu().getDizhi() + " " + AMapUtil.formatDistance(LongUtil.parseLong(shopDetail.getDianpu().getJuli())));
    }

    @OnClick(R.id.shop_call)
    void clickCall(View v){
        IntentUtil.startCall(this, presenter.getPhone());
    }

    @Override
    protected void onDestroy() {
        appbar.removeOnOffsetChangedListener(onOffsetChangedListener);
        super.onDestroy();
    }


}
