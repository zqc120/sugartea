package com.dianjiake.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.dianjiake.android.base.App;


/**
 * Created by lfs on 2017/5/15.
 */

public class IntentUtil {
    /**
     * @param c
     * @return
     */
    public static Intent getIntent(Class<?> c) {
        return new Intent(App.getInstance(), c);
    }

    /**
     * 传入context和intent 启动Activity
     *
     * @param c
     * @param i
     * @return
     */
    public static boolean startActivity(Context c, Intent i) {
        if (c != null) {
            c.startActivity(i);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 传入View 启动Activity
     *
     * @param v
     * @param i
     * @return
     */
    public static boolean startActivity(View v, Intent i) {
        if (null == v) {
            return false;
        }
        return startActivity(v.getContext(), i);
    }

    public static boolean startActivity(View v, Class<?> c) {
        return startActivity(v, getIntent(c));
    }

    public static boolean startActivity(Context c, Class<?> clz) {
        return startActivity(c, getIntent(clz));
    }

    /**
     * 打开拨号键盘
     *
     * @param c
     * @param phoneNumber
     * @return
     */
    public static boolean startCall(Context c, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        return startActivity(c, intent);
    }

}
