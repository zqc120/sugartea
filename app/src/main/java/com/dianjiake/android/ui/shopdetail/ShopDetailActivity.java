package com.dianjiake.android.ui.shopdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.data.bean.ShopDetailBean;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.PtrListLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

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
    @BindView(R.id.ptr_list)
    PtrListLayout ptrList;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.system_toolbar)
    Toolbar systemToolbar;

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
    }


}
