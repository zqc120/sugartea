package com.dianjiake.android.ui.evaluate;

import com.dianjiake.android.data.bean.OrderServiceBean;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.util.CheckEmptyUtil;

import java.util.ArrayList;

/**
 * Created by lfs on 2017/7/20.
 */

public class EvaluatePresenter implements EvaluateContract.Presenter {
    EvaluateContract.View view;
    ArrayList<OrderServiceBean> items;

    public EvaluatePresenter(EvaluateContract.View view) {
        this.view = view;
        items = new ArrayList<>();
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
    public void setServices(ArrayList<OrderServiceBean> services) {
        items.clear();
        if (CheckEmptyUtil.isEmpty(services)) {
            view.loadEmptyContent();
        } else {
            items.addAll(services);
            OrderServiceBean bean = new OrderServiceBean();
            bean.setViewType(EvaluateType.BUTTON);
            items.add(bean);
            view.loadAll();
        }

    }

    @Override
    public ArrayList<OrderServiceBean> getItems() {
        return items;
    }

}
