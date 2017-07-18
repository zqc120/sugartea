package com.dianjiake.android.common;


import android.app.Service;
import android.content.Intent;

import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;

/**
 * Created by Sacowiw on 2017/6/15.
 */

public class ActiivtyDataHelper {

    private static final String KEY_SS = "keyss";
    private static final String KEY_STAFF_SERVICE = "keysts";

    public static Intent getServiceStaffData(UserInfoBean item) {
        Intent intent = new Intent();
        intent.putExtra(KEY_SS, item);
        return intent;
    }

    public static UserInfoBean getServiceStaffBean(Intent intent) {
        return intent.getParcelableExtra(KEY_SS);
    }

    public static Intent getStaffServiceData(ServiceBean item) {
        Intent intent = new Intent();
        intent.putExtra(KEY_STAFF_SERVICE, item);
        return intent;
    }

    public static ServiceBean getStaffServiceBean(Intent intent) {
        return intent.getParcelableExtra(KEY_STAFF_SERVICE);
    }
}
