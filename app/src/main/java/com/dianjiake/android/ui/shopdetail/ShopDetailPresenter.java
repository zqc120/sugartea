package com.dianjiake.android.ui.shopdetail;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseBean;
import com.dianjiake.android.data.bean.ShopDetailBean;
import com.dianjiake.android.data.db.AppInfoDBHelper;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.AppInfoModel;
import com.dianjiake.android.data.model.LoginInfoModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/14.
 */

public class ShopDetailPresenter implements ShopDetailContract.Presenter {

    ShopDetailContract.View view;
    AppInfoModel appInfo;
    LoginInfoModel loginInfo;
    CompositeDisposable cd;
    String phone;
    boolean isCollect;

    public ShopDetailPresenter(ShopDetailContract.View view) {
        this.view = view;
        appInfo = AppInfoDBHelper.newInstance().getAppInfo();
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
        cd = new CompositeDisposable();
    }

    @Override
    public void start() {
        load();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void load() {
        Network.getInstance().shopDetail(
                BSConstant.SHOP_DETAIL,
                loginInfo.getOpenId(),
                view.getShopId(),
                appInfo.getLongitude() + "," + appInfo.getLatitude())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<BaseBean<ShopDetailBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(@NonNull BaseBean<ShopDetailBean> shopDetailBean) {
                        if (shopDetailBean.getCode() == 200&&shopDetailBean.getObj().getDianpu()!=null) {
                            view.setView(shopDetailBean.getObj());
                            phone = shopDetailBean.getObj().getDianpu().getDianhua();
                            isCollect = "1".equals(shopDetailBean.getObj().getDianpu().getShifoushoucang());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isCollect() {
        return isCollect;
    }
}
