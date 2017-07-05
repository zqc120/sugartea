package com.dianjiake.android.data.db;

import com.dianjiake.android.base.App;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.data.model.LoginInfoModelDao;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.igexin.sdk.PushManager;

import java.util.List;

/**
 * Created by lfs on 2017/5/18.
 */

public class LoginInfoDBHelper {
    public static LoginInfoDBHelper newInstance() {
        return new LoginInfoDBHelper();
    }

    private LoginInfoDBHelper() {
    }

    public LoginInfoModelDao getLoginInfoDao() {
        return DBManager.getInstance().getDaoSession().getLoginInfoModelDao();
    }

//    public long saveLoginInfo(UserInfoItemBean bean) {
//        if (bean == null) {
//            return -1;
//        }
//        LoginInfoModel model = new LoginInfoModel();
//        model.setId(1L);
//        model.setAvatar(bean.getAvatar());
//        model.setName(bean.getName());
//        model.setNickname(bean.getNickname());
//        model.setPhone(bean.getPhone());
//        model.setOpenId(bean.getOpenid());
//        model.setShopId(bean.getDianpu().getId());
//        model.setShopCover(bean.getDianpu().getCover());
//        model.setShopDesc(bean.getDianpu().getJianjie());
//        model.setShopLogo(bean.getDianpu().getLogo());
//        model.setShopName(bean.getDianpu().getMingcheng());
//        model.setOccupationAvatar(bean.getZhiyezhao());
//        model.setOccupationName(bean.getShanghunicheng());
//        model.setIntro(bean.getYuangongjianjie());
//        model.setGender(bean.getSex());
//        model.setBirthday(bean.getBirthday());
//        model.setShopStartTime(bean.getDianpu().getKaishishijian());
//        model.setShopEndTime(bean.getDianpu().getJieshushijian());
//        model.setStaffLevel(bean.getShanghujibie());
//
//        //绑定个推别名
//        PushManager.getInstance().bindAlias(App.getInstance(), bean.getOpenid());
//
//        return getLoginInfoDao().insertOrReplace(model);
//    }

    public LoginInfoModel getLoginInfo() {
        List<LoginInfoModel> list = getLoginInfoDao().queryBuilder().limit(1).list();
        if (CheckEmptyUtil.isEmpty(list)) {
            return new LoginInfoModel();
        } else {
            return list.get(0);
        }
    }

    public boolean isLogin() {
        return !CheckEmptyUtil.isEmpty(getLoginInfoDao().queryBuilder().limit(1).list());
    }

    /**
     * 退出登录
     */
    public void logout() {
        //解绑个推别名
        PushManager.getInstance().unBindAlias(App.getInstance(), getLoginInfo().getOpenId(), true);

        LoginInfoModelDao.dropTable(DBManager.getInstance().getDaoMaster().getDatabase(), true);
        LoginInfoModelDao.createTable(DBManager.getInstance().getDaoMaster().getDatabase(), true);
    }

}
