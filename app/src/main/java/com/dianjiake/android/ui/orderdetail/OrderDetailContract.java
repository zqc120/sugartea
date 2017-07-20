package com.dianjiake.android.ui.orderdetail;

import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseView;
import com.dianjiake.android.data.bean.OrderBean;

/**
 * Created by lfs on 2017/7/20.
 */

public interface OrderDetailContract {
    interface View extends BaseView<Presenter> {
        void setView(OrderBean bean);
    }

    interface Presenter extends BasePresenter {
    }
}
