package com.dianjiake.android.ui.shopweb;

import android.os.Parcel;
import android.os.Parcelable;

import com.dianjiake.android.data.bean.ServiceBean;

/**
 * Created by lfs on 2017/7/17.
 */

public class ServiceProvider implements BaseProvider<ServiceBean>,Parcelable {
    ServiceBean serviceBean;

    public ServiceProvider(ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }

    @Override
    public String getTitle() {
        return serviceBean.getName();
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public ServiceBean getBean() {
        return serviceBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.serviceBean, flags);
    }

    protected ServiceProvider(Parcel in) {
        this.serviceBean = in.readParcelable(ServiceBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ServiceProvider> CREATOR = new Parcelable.Creator<ServiceProvider>() {
        @Override
        public ServiceProvider createFromParcel(Parcel source) {
            return new ServiceProvider(source);
        }

        @Override
        public ServiceProvider[] newArray(int size) {
            return new ServiceProvider[size];
        }
    };
}
