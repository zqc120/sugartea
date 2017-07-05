package com.dianjiake.android.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.support.annotation.LayoutRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dianjiake.android.base.App;

import java.lang.reflect.Field;

/**
 * Created by lfs on 2017/5/12.
 */

public class UIUtil {
    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return 0;
        }
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getDimensionPixelSize(x);
        } catch (Exception e) {

        }
        return 0;
    }

    public static int getDimensionPixelSize(@DimenRes int dp) {
        return App.getInstance().getResources().getDimensionPixelSize(dp);
    }

    public static float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, App.getInstance().getResources().getDisplayMetrics());

    }

    public static View inflate(@LayoutRes int layout, Context context) {
        return LayoutInflater.from(context).inflate(layout, null);
    }

    public static View inflate(@LayoutRes int layout, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
    }

    public static int getScreenWidth() {
        return App.getInstance().getResources().getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return App.getInstance().getResources().getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getColor(int colorRes) {
        return App.getInstance().getResources().getColor(colorRes);
    }

}
