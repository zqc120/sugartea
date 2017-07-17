package com.dianjiake.android.ui.shopweb;

/**
 * Created by lfs on 2017/7/17.
 */

public interface BaseProvider<T> {
    String getTitle();

    String getUrl();

    T getBean();
}
