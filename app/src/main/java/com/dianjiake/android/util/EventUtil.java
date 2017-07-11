package com.dianjiake.android.util;


import com.dianjiake.android.event.LocationEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lfs on 2017/6/19.
 */

public class EventUtil {
    public static void postLocationEvent(LocationEvent event){
        EventBus.getDefault().post(event);
    }
}
