package com.dianjiake.android.ui.evaluate;

import com.dianjiake.android.data.bean.EvaluateBean;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.data.bean.OrderServiceBean;
import com.dianjiake.android.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfs on 2017/7/20.
 */

public class EvaluatePresenter implements EvaluateContract.Presenter {
    EvaluateContract.View view;
    ArrayList<OrderServiceBean> items;
    List<EvaluateBean> evaluates;

    public EvaluatePresenter(EvaluateContract.View view) {
        this.view = view;
        items = new ArrayList<>();
        evaluates = new ArrayList<>();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void load(boolean isReload) {

    }

    @Override
    public void setServices(OrderBean orderBean) {
        ArrayList<OrderServiceBean> services = orderBean.getDingdanfuwu();
        items.clear();
        if (CheckEmptyUtil.isEmpty(services)) {
            view.loadEmptyContent();
        } else {
            items.addAll(services);
            for (OrderServiceBean b : services) {
                EvaluateBean evaluateBean = new EvaluateBean();
                evaluateBean.setRate(5);
                evaluates.add(evaluateBean);
            }
            OrderServiceBean bean = new OrderServiceBean();
            bean.setViewType(EvaluateType.BUTTON);
            items.add(bean);
            view.loadAll();
        }
    }

    @Override
    public void setComment(String comment, int position) {
        if (evaluates.size() > position) {
            evaluates.get(position).setComemnt(comment);
        }
    }

    @Override
    public void setRate(int rate, int position) {
        if (evaluates.size() > position) {
            evaluates.get(position).setRate(rate);
        }
    }

    @Override
    public ArrayList<OrderServiceBean> getItems() {
        return items;
    }

}
