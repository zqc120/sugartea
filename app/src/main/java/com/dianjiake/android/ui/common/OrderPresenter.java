package com.dianjiake.android.ui.common;


import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.OrderBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/20.
 */

public class OrderPresenter extends BaseOrderPresenter {

    public OrderPresenter(OrderView view) {
        super(view);
    }

    @Override
    public Observable<BaseListBean<OrderBean>> provideApi() {
        return Network.getInstance().orders(BSConstant.ORDER_LIST, loginInfo.getOpenId(), page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
