package com.dianjiake.android;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseTranslateActivity;

public class MainActivity extends BaseTranslateActivity {

    @Override
    public int provideContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
