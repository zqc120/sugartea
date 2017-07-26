package com.dianjiake.android.ui.common;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseBean;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.db.MsgDBHelper;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.event.RefreshOrderListEvent;
import com.dianjiake.android.request.OrderListObserver;
import com.dianjiake.android.util.EventUtil;
import com.dianjiake.android.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/20.
 */

public abstract class BaseOrderPresenter implements BaseListPresenter {
    public OrderView view;
    public List<OrderBean> items;
    public CompositeDisposable cd;
    public LoginInfoModel loginInfo;
    public int page = 1;
    MsgDBHelper msgDBHelper;

    public BaseOrderPresenter(OrderView view) {
        this.view = view;
        items = new ArrayList<>();
        cd = new CompositeDisposable();
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
        EventBus.getDefault().register(this);
        msgDBHelper = MsgDBHelper.newInstance();
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
        page = 1;
        load(true);
    }

    @Override
    public void load(final boolean isReload) {
        cd.clear();
        provideApi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new OrderListObserver<OrderBean>() {
                    @Override
                    public void onSuccess(List<OrderBean> list, boolean isAll, int noCommentCount) {
                        if (isReload) {
                            items.clear();
                        }

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

    public void cancelOrder(final OrderBean orderBean, final int position) {
        cd.clear();
        view.showCancelPD();
        Network.getInstance().orderCancel(BSConstant.ORDER_CANCEL, loginInfo.getOpenId(), orderBean.getOrdernum())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(@NonNull BaseBean baseBean) {
                        view.dismissCancelPD();
                        if (baseBean.getCode() == 200) {
                            ToastUtil.showShortToast("取消预约成功");
                            orderBean.setStatus("4");
                            items.set(position, orderBean);
                            view.loadComplete();
                        } else {
                            ToastUtil.showShortToast("取消预约失败，请稍候重试");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtil.showShortToast("取消预约失败，请稍候重试");
                        view.dismissCancelPD();
                    }

                    @Override
                    public void onComplete() {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshOrderListEvent event) {
        reload();
    }

    public boolean haveUnreadMsg() {
        return msgDBHelper.isUnread();
    }

    public abstract void clickHolder(OrderBean orderBean, int position);

    public abstract void clickCall(OrderBean orderBean, int position);

    public abstract void clickReSub(OrderBean orderBean, int position);

    public abstract void clickCancel(OrderBean orderBean, int position);

    public abstract void clickEvaluate(OrderBean orderBean, int position);

    public abstract Observable<BaseListBean<OrderBean>> provideApi();

}
