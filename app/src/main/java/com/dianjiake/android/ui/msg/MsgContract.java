package com.dianjiake.android.ui.msg;

import com.dianjiake.android.base.BaseListPresenter;
import com.dianjiake.android.base.BaseListView;
import com.dianjiake.android.data.bean.MsgBean;

import java.util.List;

/**
 * Created by lfs on 2017/7/26.
 */

public interface MsgContract {
    interface View extends BaseListView {
        void open(MsgBean msgBean);
    }

    interface Presenter extends BaseListPresenter {
        List<MsgBean> getItems();

        void clickItem(MsgBean msgBean, int position);
    }
}
