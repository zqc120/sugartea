package com.dianjiake.android.ui.main;

import android.widget.RadioGroup;

import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BaseListView;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.event.LocationEvent;

import java.util.List;

/**
 * Created by lfs on 2017/7/7.
 */

public interface HomeContract {
    interface View extends BaseListView {

        void setLocationName(String locationName);

        void judgeScrollY(int totalY);

        void showPD();

        void dismissPD();
    }

    interface Presenter extends BaseListPresenter {
        List<HomeShopBean> getItems();

        void addRG(RadioGroup rg);
    }
}
