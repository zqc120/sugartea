package com.dianjiake.android.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lfs on 2017/7/20.
 */

public class EvaluateBean implements Parcelable {
    private int rate;
    private String comemnt;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComemnt() {
        return comemnt;
    }

    public void setComemnt(String comemnt) {
        this.comemnt = comemnt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rate);
        dest.writeString(this.comemnt);
    }

    public EvaluateBean() {
    }

    protected EvaluateBean(Parcel in) {
        this.rate = in.readInt();
        this.comemnt = in.readString();
    }

    public static final Parcelable.Creator<EvaluateBean> CREATOR = new Parcelable.Creator<EvaluateBean>() {
        @Override
        public EvaluateBean createFromParcel(Parcel source) {
            return new EvaluateBean(source);
        }

        @Override
        public EvaluateBean[] newArray(int size) {
            return new EvaluateBean[size];
        }
    };
}
