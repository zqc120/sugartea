package com.dianjiake.android.util;

/**
 * Created by lfs on 2017/7/10.
 */

public class AMapUtil {

    /**
     * 格式化距离
     */
    public static String formatDistance(long distance) {
        if (distance < 1000) {
            return distance + "m";
        } else {
            float d = distance / (float) 1000;
            return String.format("%.2f", d) + "km";
        }
    }
}
