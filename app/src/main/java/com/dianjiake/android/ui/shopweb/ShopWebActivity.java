package com.dianjiake.android.ui.shopweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseWebViewActivity;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.ui.shopdetail.ShopDetailActivity;
import com.dianjiake.android.ui.subscribe.SubscribeActivity;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.dialog.ShareDialog;
import com.google.gson.Gson;

import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/17.
 */

public class ShopWebActivity extends BaseWebViewActivity {

    public static final String TYPE_SERVICE = "service";
    public static final String TYPE_STAFF = "staff";
    public static final String KEY_PROVIDE = "KEY_PROVIDE";

    BaseProvider provider;

    public static Intent getServiceDetail(ServiceBean serviceBean) {
        Intent intent = IntentUtil.getIntent(ShopWebActivity.class);
        intent.setType(TYPE_SERVICE);
        intent.putExtra(KEY_PROVIDE, new ServiceProvider(serviceBean));
        return intent;
    }

    public static Intent getStaffDetaill(UserInfoBean userInfoBean) {
        Intent intent = IntentUtil.getIntent(ShopWebActivity.class);
        intent.setType(TYPE_STAFF);
        intent.putExtra(KEY_PROVIDE, new StaffProvider(userInfoBean));
        return intent;
    }

    @Override
    protected void provideIntent(Intent intent) {
        provider = intent.getParcelableExtra(KEY_PROVIDE);
    }

    @Override
    protected String getToolbarTitle() {
        return provider.getTitle();
    }

    @Override
    protected String getUrl() {
        return provider.getUrl();
    }

    @Override
    protected void addView(FrameLayout view) {

    }

    @Override
    @SuppressLint("AddJavascriptInterface")
    protected void initWebView(WebView webView) {
        mToolbarRight.setVisibility(View.VISIBLE);
        mToolbarRight.setImageResource(R.drawable.ic_toolbar_share);
        webView.addJavascriptInterface(new JS(), "storehome");
    }


    class JS {
        @JavascriptInterface
        public void getService(String s) {
            ServiceBean serviceBean;
            try {
                serviceBean = new Gson().fromJson(s, ServiceBean.class);
                startActivity(SubscribeActivity.getStartIntent(serviceBean, null));
            } catch (Exception e) {

            }
        }

        @JavascriptInterface
        public void shop(String s) {
            HomeShopBean shopEntity;
            try {
                shopEntity = new Gson().fromJson(s, HomeShopBean.class);
                startActivity(ShopDetailActivity.getStartIntent(shopEntity.getId()));
            } catch (Exception e) {

            }
        }
        
        @JavascriptInterface
        public void getStaffService(String staff, String service) {
            ServiceBean serviceBean;
            UserInfoBean userInfoBean;
            try {
                userInfoBean = new Gson().fromJson(staff, UserInfoBean.class);
                serviceBean = new Gson().fromJson(service, ServiceBean.class);
                startActivity(SubscribeActivity.getStartIntent(serviceBean, userInfoBean));
            } catch (Exception e) {

            }
        }
    }

    @OnClick(R.id.toolbar_icon_right)
    void clickShare(View v) {
        ShareDialog.Show(this, shareTitle, "糖茶", shareUrl, shareImage);
    }

}
