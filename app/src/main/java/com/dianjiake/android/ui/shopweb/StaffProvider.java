package com.dianjiake.android.ui.shopweb;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.StringBuilderPrinter;

import com.dianjiake.android.data.bean.UserInfoBean;

/**
 * Created by lfs on 2017/7/17.
 */

public class StaffProvider implements BaseProvider<UserInfoBean>, Parcelable {
    UserInfoBean userInfoBean;

    public StaffProvider(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    @Override
    public String getTitle() {
        StringBuilder sb = new StringBuilder(userInfoBean.getName());
        if (userInfoBean.getDianpu() != null) {
            sb.append("-")
                    .append(userInfoBean.getDianpu().getMingcheng());
        }
        return sb.toString();
    }

    @Override
    public String getUrl() {
        return "http://www.quanminlebang.com/m/activity/revenue/mainClerk.html?shanghuid=" + userInfoBean.getShanghuid() + "&openid=" + userInfoBean.getOpenid();
    }

    @Override
    public UserInfoBean getBean() {
        return userInfoBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userInfoBean, flags);
    }

    protected StaffProvider(Parcel in) {
        this.userInfoBean = in.readParcelable(UserInfoBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<StaffProvider> CREATOR = new Parcelable.Creator<StaffProvider>() {
        @Override
        public StaffProvider createFromParcel(Parcel source) {
            return new StaffProvider(source);
        }

        @Override
        public StaffProvider[] newArray(int size) {
            return new StaffProvider[size];
        }
    };
}
