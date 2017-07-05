package com.dianjiake.android.base;

/**
 * Created by lfs on 2017/6/7.
 */

public interface BaseListPresenter extends BasePresenter {

    void reload();

    void load(boolean isReload);
}
