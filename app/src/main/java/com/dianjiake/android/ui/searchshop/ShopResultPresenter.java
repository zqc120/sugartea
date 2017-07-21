package com.dianjiake.android.ui.searchshop;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.request.ListObserver;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/12.
 */

public class ShopResultPresenter extends BaseSearchPresenter<HomeShopBean, ShopResultContract.View> {

    public ShopResultPresenter(ShopResultContract.View view) {
        super(view);
    }

    @Override
    public void load(boolean isReload) {
        cd.clear();
        if(isReload){
            view.loading();
        }
        Network.getInstance().searchShop(
                BSConstant.SEARCH_SHOP,
                loginInfo.getOpenId(),
                keyword,
                0,
                appInfo.getLongitude() + "," + appInfo.getLatitude(),
                page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ListObserver<HomeShopBean>() {
                    @Override
                    public void onSuccess(List<HomeShopBean> list, boolean isAll) {
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

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.loadNetworkError();
                    }
                });
    }
}
