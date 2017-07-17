package com.dianjiake.android.ui.shopweb;

import android.content.Intent;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.dianjiake.android.base.BaseWebViewActivity;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.util.IntentUtil;

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
    protected void initWebView(WebView webView) {

    }
}
