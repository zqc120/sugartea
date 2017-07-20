package com.dianjiake.android.ui.common;

import com.dianjiake.android.constant.Constant;

/**
 * Created by lfs on 2017/7/20.
 */

public class OrderStatus {
    public static final int NO_CONFIRM = 0;
    public static final int CONFIRM = 1;
    public static final int COMPLETE = 2;
    public static final int CANCEL = 3;
    public static final int RUNNING = 4;

    public static int getStatus(String status) {
        switch (status) {
            case "0":
            case "1":
                return NO_CONFIRM;
            case "2":
                return CONFIRM;
            case "3":
                return COMPLETE;
            case "4":
            case "5":
                return CANCEL;
            default:
                return RUNNING;
        }
    }

    public static String getStatusText(String status) {
        switch (status) {
            case "0":
            case "1":
                return "等待商家确认";
            case "2":
                return "商家已确认";
            case "3":
                return "已完成";
            case "4":
            case "5":
                return "已取消";
            default:
                return "商家已确认";
        }
    }
}
