package com.dianjiake.android.base;

/**
 * Created by lfs on 2017/6/7.
 */

public interface BaseListView {
    void loading();

    void loadComplete();

    void loadAll();

    void loadEmptyContent();

    void loadNetworkError();
}
