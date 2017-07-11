package com.dianjiake.android.ui.main;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.data.db.AppInfoDBHelper;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.AppInfoModel;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.event.LocationEvent;
import com.dianjiake.android.request.ListObserver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/7.
 */

public class HomePresenter implements HomeContract.Presenter {
    int order;
    int page = 1;
    HomeContract.View view;
    LoginInfoModel loginInfo;
    CompositeDisposable cd;
    List<HomeShopBean> items = new ArrayList<>();
    AppInfoModel appInfo;

    String locationName;
    String longitude;
    String latitude;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
        appInfo = AppInfoDBHelper.newInstance().getAppInfo();
        cd = new CompositeDisposable();

        locationName = appInfo.getLocationName();
        longitude = appInfo.getLongitude();
        latitude = appInfo.getLatitude();
        view.setLocationName(locationName);
        EventBus.getDefault().register(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        cd.clear();
    }

    @Override
    public void reload() {
        page = 1;
        items.clear();
        load(true);
    }

    @Override
    public void load(final boolean isReload) {
        Network.getInstance().homeShop(BSConstant.SHOP_LIST, loginInfo.getOpenId(), order, longitude + "," + latitude, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new ListObserver<HomeShopBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.loadNetworkError();
                    }

                    @Override
                    public void onSuccess(List<HomeShopBean> list, boolean isAll) {
                        if (isReload) {
                            HomeShopBean temp = new HomeShopBean();
                            temp.setViewType(HomeType.AD);
                            items.add(0, temp);
                            HomeShopBean filter = new HomeShopBean();
                            filter.setViewType(HomeType.FILTER);
                            items.add(filter);
                        }
                        items.addAll(list);
                        page++;
                        if (isAll) {
                            view.loadAll();
                        } else {
                            view.loadComplete();
                        }
                    }

                    @Override
                    public void onEmpty() {
                        view.loadEmptyContent();
                    }

                    @Override
                    public void onAll() {
                        view.loadAll();
                    }
                });
    }

    @Override
    public List<HomeShopBean> getItems() {
        return items;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationChange(LocationEvent event) {
        locationName = event.locationName;
        longitude = event.longitude;
        latitude = event.latitude;
        view.setLocationName(event.locationName);
    }
}
