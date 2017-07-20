package com.dianjiake.android.ui.orderdetail;

import com.dianjiake.android.ui.main.OrderContract;

/**
 * Created by lfs on 2017/7/20.
 */

public class OrderDetailPresenter implements OrderDetailContract.Presenter {
    OrderDetailContract.View view;

    public OrderDetailPresenter(OrderDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
