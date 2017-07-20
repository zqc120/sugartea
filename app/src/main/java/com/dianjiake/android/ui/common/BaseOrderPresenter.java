package com.dianjiake.android.ui.common;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.request.OrderListObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/20.
 */

public abstract class BaseOrderPresenter implements BaseListPresenter {
    OrderView view;
    List<OrderBean> items;
    CompositeDisposable cd;
    public LoginInfoModel loginInfo;
    public int page = 1;

    public BaseOrderPresenter(OrderView view) {
        this.view = view;
        items = new ArrayList<>();
        cd = new CompositeDisposable();
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
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
        cd.clear();
       provideApi().subscribeWith(new OrderListObserver<OrderBean>() {
                    @Override
                    public void onSuccess(List<OrderBean> list, boolean isAll, int noCommentCount) {
                        if (isReload && view.getShowNoCommentHolder() && noCommentCount > 0) {
                            OrderBean orderBean = new OrderBean();
                            orderBean.setViewType(OrderViewType.HEADER);
                            items.add(orderBean);
                            view.setNoCommentCount(noCommentCount);
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


    public List<OrderBean> getItems() {
        return items;
    }

    public void removeHeaderItem() {
        if (items.size() > 0 && items.get(0).getViewType() == OrderViewType.HEADER) {
            items.remove(0);
            view.loadComplete();
        }
    }

    public abstract Observable<BaseListBean<OrderBean>> provideApi();
}
