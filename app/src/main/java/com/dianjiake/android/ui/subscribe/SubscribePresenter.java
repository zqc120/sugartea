package com.dianjiake.android.ui.subscribe;

import android.support.v4.util.ArrayMap;

import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.view.widget.ServiceItemView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lfs on 2017/7/18.
 */

public class SubscribePresenter implements SubscribeContract.Presenter {
    CompositeDisposable cd;
    SubscribeContract.View view;

    ArrayMap<Integer, ServiceItemView> otherServices;
    ArrayMap<Integer, ServiceBean> chooseServices;
    ArrayMap<Integer, UserInfoBean> chooseStaffs;
    ServiceBean defaultService;
    UserInfoBean defaultUserInfo;

    int otherServiceTag = 0xa53;
    String shopId, serviceId, staffOpenId;


    public SubscribePresenter(SubscribeContract.View view) {
        this.view = view;
        cd = new CompositeDisposable();
        otherServices = new ArrayMap<>();
        chooseServices = new ArrayMap<>();
        chooseStaffs = new ArrayMap<>();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        cd.clear();
    }

    @Override
    public void setDefaultValue(ServiceBean serviceBean, UserInfoBean userInfo) {
        defaultService = serviceBean;
        defaultUserInfo = userInfo;
        if (serviceBean != null) {
            shopId = serviceBean.getShanghuid();
            serviceId = serviceBean.getId();
        }
        if (userInfo != null) {
            shopId = userInfo.getShanghuid();
            staffOpenId = userInfo.getOpenid();
        }
    }

    @Override
    public ServiceBean getDefaultService() {
        return defaultService;
    }

    @Override
    public UserInfoBean getDefaultUserInfo() {
        return defaultUserInfo;
    }

    @Override
    public void addServiceItem(ServiceItemView serviceItemView) {
        serviceItemView.setTag(otherServiceTag);
        serviceItemView.initServiceAndStaff(defaultService, defaultUserInfo);
        otherServices.put((int) serviceItemView.getTag(), serviceItemView);
        otherServiceTag++;
    }

    @Override
    public String getShopId() {
        return shopId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public String getOpenId() {
        return staffOpenId;
    }
}
