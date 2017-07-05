package com.dianjiake.android.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by Sacowiw on 2017/5/17.
 */
@Entity
public class AppInfoModel {
    @Id
    @Unique
    private Long id;
    private String cid;//个推cid
    @Generated(hash = 344762656)
    public AppInfoModel(Long id, String cid) {
        this.id = id;
        this.cid = cid;
    }
    @Generated(hash = 1825615946)
    public AppInfoModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCid() {
        return this.cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }

}
