package com.dianjiake.android.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by lfs on 2017/5/18.
 */

@Entity
public class LoginInfoModel {
    @Id
    @Unique
    private Long id;
    private String openId;
    private String name;
    private String nickname;
    private String phone;
    private String avatar;
    private String gender;
    private String intro;
    private String birthday;
    private String occupationAvatar;
    private String occupationName;
    private String shopId;
    private String shopName;
    private String shopLogo;
    private String shopCover;
    private String shopDesc;
    private String shopStartTime;
    private String shopEndTime;
    private String staffLevel;

    @Generated(hash = 2017414386)
    public LoginInfoModel(Long id, String openId, String name, String nickname, String phone, String avatar, String gender,
            String intro, String birthday, String occupationAvatar, String occupationName, String shopId, String shopName,
            String shopLogo, String shopCover, String shopDesc, String shopStartTime, String shopEndTime,
            String staffLevel) {
        this.id = id;
        this.openId = openId;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.intro = intro;
        this.birthday = birthday;
        this.occupationAvatar = occupationAvatar;
        this.occupationName = occupationName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopLogo = shopLogo;
        this.shopCover = shopCover;
        this.shopDesc = shopDesc;
        this.shopStartTime = shopStartTime;
        this.shopEndTime = shopEndTime;
        this.staffLevel = staffLevel;
    }

    @Generated(hash = 261654949)
    public LoginInfoModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopLogo() {
        return this.shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopCover() {
        return this.shopCover;
    }

    public void setShopCover(String shopCover) {
        this.shopCover = shopCover;
    }

    public String getShopDesc() {
        return this.shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOccupationAvatar() {
        return this.occupationAvatar;
    }

    public void setOccupationAvatar(String occupationAvatar) {
        this.occupationAvatar = occupationAvatar;
    }

    public String getOccupationName() {
        return this.occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getShopStartTime() {
        return this.shopStartTime;
    }

    public void setShopStartTime(String shopStartTime) {
        this.shopStartTime = shopStartTime;
    }

    public String getShopEndTime() {
        return this.shopEndTime;
    }

    public void setShopEndTime(String shopEndTime) {
        this.shopEndTime = shopEndTime;
    }

    public String getStaffLevel() {
        return this.staffLevel;
    }

    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }

}
