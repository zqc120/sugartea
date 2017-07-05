package com.dianjiake.android.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dianjiake.android.util.UIUtil;


/**
 * Created by lfs on 2017/5/11.
 */

public abstract class BaseTranslateActivity<P extends BasePresenter> extends BaseActivity<P> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    protected void setToolbarSpaceHeight(final View space) {
        space.post(new Runnable() {
            @Override
            public void run() {
                if (space != null) {
                    ViewGroup.LayoutParams lp = space.getLayoutParams();
                    lp.height = UIUtil.getStatusBarHeight();
                    space.setLayoutParams(lp);
                    space.postInvalidate();
                }
            }
        });
    }
}
