package com.dianjiake.android.ui.main;

import com.dianjiake.android.base.App;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.igexin.sdk.PushManager;

/**
 * Created by lfs on 2017/6/7.
 */

public class MainPresenter implements MainContract.Presenter {

    @Override
    public void start() {
        PushManager.getInstance().bindAlias(App.getInstance(), LoginInfoDBHelper.newInstance().getLoginInfo().getOpenId());
    }

    @Override
    public void onDestroy() {

    }
}
