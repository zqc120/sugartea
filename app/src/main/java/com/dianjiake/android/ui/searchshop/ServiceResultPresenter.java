package com.dianjiake.android.ui.searchshop;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.request.ListObserver;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/12.
 */

public class ServiceResultPresenter extends BaseSearchPresenter<ServiceBean, ServiceResultContract.View> {

    public ServiceResultPresenter(ServiceResultContract.View view) {
        super(view);
    }

    @Override
    public void load(boolean isReload) {
        cd.clear();
        if(isReload){
            view.loading();
        }
        Network.getInstance().searchService(
                BSConstant.SEARCH_SERVICE,
                loginInfo.getOpenId(),
                keyword,
                0,
                appInfo.getLongitude() + "," + appInfo.getLatitude(),
                page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ListObserver<ServiceBean>() {
                    @Override
                    public void onSuccess(List<ServiceBean> list, boolean isAll) {
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
