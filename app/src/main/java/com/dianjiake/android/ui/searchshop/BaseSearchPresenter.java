package com.dianjiake.android.ui.searchshop;

import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BaseListView;
import com.dianjiake.android.data.db.AppInfoDBHelper;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.AppInfoModel;
import com.dianjiake.android.data.model.LoginInfoModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lfs on 2017/7/12.
 */

public abstract class BaseSearchPresenter<T, V extends BaseListView> implements BaseListPresenter {
    protected V view;
    List<T> items;
    int page = 1;
    String keyword;
    CompositeDisposable cd;
    AppInfoModel appInfo;
    LoginInfoModel loginInfo;

    public BaseSearchPresenter(V view) {
        super();
        this.view = view;

    }

    private BaseSearchPresenter() {
        items = new ArrayList<>();
        cd = new CompositeDisposable();
        appInfo = AppInfoDBHelper.newInstance().getAppInfo();
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
        EventBus.getDefault().register(this);
    }

    @Override
    public void start() {


    }


    @Override
    public void onDestroy() {
        cd.clear();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void reload() {
        items.clear();
        page = 1;
        load(true);
    }


    public List<T> getItems() {
        return items;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setKeyword(String keyword) {
        this.keyword = keyword;
        reload();
    }
}
