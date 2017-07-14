package com.dianjiake.android.ui.shopdetail;

import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseView;
import com.dianjiake.android.data.bean.ShopDetailBean;

/**
 * Created by lfs on 2017/7/14.
 */

public interface ShopDetailContract {
    interface View extends BaseView<Presenter> {

        String getShopId();

        void setView(ShopDetailBean shopDetail);
    }

    interface Presenter extends BasePresenter {
        void load();
    }
}
