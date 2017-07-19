package com.dianjiake.android.ui.main;

import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseBean;
import com.dianjiake.android.data.bean.LoginBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/19.
 */

public class MinePresenter implements MineContract.Presenter {
    LoginInfoDBHelper loginInfoDBHelper;
    MineContract.View view;
    CompositeDisposable cd;

    public MinePresenter(MineContract.View view) {
        this.view = view;
        cd = new CompositeDisposable();
        loginInfoDBHelper = LoginInfoDBHelper.newInstance();
    }

    @Override
    public void start() {
        view.setPhone(loginInfoDBHelper.getLoginInfo().getPhone());
        view.setAvatar(loginInfoDBHelper.getLoginInfo().getAvatar());
    }

    @Override
    public void onDestroy() {
        cd.clear();
    }

    @Override
    public void load() {
        cd.clear();
        Network.getInstance().getUserInfo(
                BSConstant.USER_INFO,
                loginInfoDBHelper.getLoginInfo().getOpenId(),
                null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<BaseBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(@NonNull BaseBean<LoginBean> baseBean) {
                        if (baseBean.getCode() == 200) {
                            view.setViews(baseBean.getObj().getUser());
                            loginInfoDBHelper.saveLoginInfo(baseBean.getObj().getUser());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
