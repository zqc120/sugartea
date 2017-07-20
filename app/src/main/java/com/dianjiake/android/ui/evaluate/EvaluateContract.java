package com.dianjiake.android.ui.evaluate;

import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BaseListView;
import com.dianjiake.android.data.bean.OrderServiceBean;

import java.util.ArrayList;

/**
 * Created by lfs on 2017/7/20.
 */

public interface EvaluateContract {
    interface View extends BaseListView {
    }

    interface Presenter extends BaseListPresenter {
        void setServices(ArrayList<OrderServiceBean> services);

        void setComment(String comment, int position);

        void setRate(int rate, int position);

        ArrayList<OrderServiceBean> getItems();
    }
}
