package com.dianjiake.android.ui.subscribe;

import android.app.Service;

import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseView;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.view.widget.ServiceItemView;

/**
 * Created by lfs on 2017/7/18.
 */

public interface SubscribeContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void setDefaultValue(ServiceBean serviceBean, UserInfoBean userInfo);

        ServiceBean getDefaultService();

        UserInfoBean getDefaultUserInfo();

        void addServiceItem(ServiceItemView serviceItemView);

        String getShopId();

        String getServiceId();

        String getOpenId();
    }
}
