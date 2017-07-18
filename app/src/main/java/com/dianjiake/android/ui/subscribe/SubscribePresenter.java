package com.dianjiake.android.ui.subscribe;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lfs on 2017/7/18.
 */

public class SubscribePresenter implements SubscribeContract.Presenter {
    CompositeDisposable cd;
    SubscribeContract.View view;

    public SubscribePresenter(SubscribeContract.View view) {
        this.view = view;
        cd = new CompositeDisposable();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        cd.clear();
    }
}
