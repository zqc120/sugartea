package com.dianjiake.android.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dianjiake.android.MainActivity;
import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.util.IntentUtil;


/**
 * Created by lfs on 2017/5/15.
 */

public class SplashActivity extends BaseTranslateActivity<SplashPresenter> implements SplashContract.SplashView {
    @Override
    public int provideContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashPresenter getPresenter() {
        return new SplashPresenter(this);
    }


    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        presenter.getPhoneInfo();
    }


    @Override
    public void setPresenter(SplashContract.Presenter presenter) {

    }


    @Override
    public void startLoginActivity() {
        IntentUtil.startActivity(this, MainActivity.class);
        finish();
    }

    @Override
    public void startMainActivity() {
        IntentUtil.startActivity(this, MainActivity.class);
        finish();
    }
}
