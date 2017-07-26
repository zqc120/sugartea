package com.dianjiake.android.ui.common;


import android.content.DialogInterface;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.ui.evaluate.EvaluateActivity;
import com.dianjiake.android.ui.orderdetail.OrderDetailActivity;
import com.dianjiake.android.ui.subscribe.SubscribeActivity;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.dialog.NormalAlertDialog;

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
    public void clickHolder(OrderBean orderBean, int position) {
        IntentUtil.startActivity(view.getViewContext(), OrderDetailActivity.getStartIntent(orderBean));
    }

    @Override
    public void clickCall(OrderBean orderBean, int position) {
        if (orderBean.getDianpu() != null) {
            IntentUtil.startCall(view.getViewContext(), orderBean.getDianpu().getDianhua());
        }
    }

    @Override
    public void clickReSub(OrderBean orderBean, int position) {
        IntentUtil.startActivity(view.getViewContext(), SubscribeActivity.getStartIntent(null, null));
    }

    @Override
    public void clickCancel(final OrderBean orderBean, final int position) {
        NormalAlertDialog alertDialog = NormalAlertDialog.newInstance("确定要取消预约？", true, true);
        alertDialog.setOnButtonClick(new NormalAlertDialog.OnButtonClick() {
            @Override
            public void click(int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    cancelOrder(orderBean, position);
                }
            }
        });
        alertDialog.showDialog(view.provideFragmentManager(), "cancel");
    }

    @Override
    public void clickEvaluate(OrderBean orderBean, int position) {
        IntentUtil.startActivity(view.getViewContext(), EvaluateActivity.getStartIntent(orderBean));
    }

    @Override
    public Observable<BaseListBean<OrderBean>> provideApi() {
        return Network.getInstance().orders(BSConstant.ORDER_LIST, loginInfo.getOpenId(), page);
    }


}
