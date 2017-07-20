package com.dianjiake.android.ui.orderdetail;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseBean;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.ui.main.OrderContract;
import com.dianjiake.android.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/20.
 */

public class OrderDetailPresenter implements OrderDetailContract.Presenter {
    OrderDetailContract.View view;
    OrderBean orderBean;
    CompositeDisposable cd;
    LoginInfoModel loginInfo;

    public OrderDetailPresenter(OrderDetailContract.View view) {
        this.view = view;
        cd = new CompositeDisposable();
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public void cancelOrder() {
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
                            view.setView(orderBean);
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
}
