package com.dianjiake.android.util;


import com.dianjiake.android.data.bean.EvaluateBean;
import com.dianjiake.android.event.LocationEvent;
import com.dianjiake.android.event.LogOutEvent;
import com.dianjiake.android.event.LoginEvent;
import com.dianjiake.android.event.RefreshOrderListEvent;
import com.dianjiake.android.event.SearchShopEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lfs on 2017/6/19.
 */

public class EventUtil {
    public static void postLocationEvent(LocationEvent event) {
        EventBus.getDefault().post(event);
    }


    public static void postSearchShopEvent(String search) {
        EventBus.getDefault().post(new SearchShopEvent(search));
    }

    public static void postLoginEvent() {
        EventBus.getDefault().post(new LoginEvent());
    }

    public static void postLogOutEvent() {
        EventBus.getDefault().post(new LogOutEvent());
    }

    public static void postRefreshOrderList() {
        EventBus.getDefault().post(new RefreshOrderListEvent());
    }
}
