package com.dianjiake.android.data.db;



import com.dianjiake.android.data.model.AppInfoModel;
import com.dianjiake.android.data.model.AppInfoModelDao;
import com.dianjiake.android.util.CheckEmptyUtil;

import java.util.List;

/**
 * Created by Sacowiw on 2017/5/17.
 */

public class AppInfoDBHelper {


    public static AppInfoDBHelper newInstance() {
        return new AppInfoDBHelper();
    }

    private AppInfoDBHelper() {

    }

    public AppInfoModelDao getAppInfoDao() {
        return DBManager.getInstance().getDaoSession().getAppInfoModelDao();
    }

    public AppInfoModel getAppInfo() {
        List<AppInfoModel> appInfoModelList = getAppInfoDao().queryBuilder().limit(1).list();
        if (CheckEmptyUtil.isEmpty(appInfoModelList)) {
            return new AppInfoModel();
        } else {
            return appInfoModelList.get(0);
        }
    }

    public void save(AppInfoModel appInfoModel) {
        if (appInfoModel != null) {
            appInfoModel.setId(1L);
        }
        getAppInfoDao().insertOrReplace(appInfoModel);
    }

    public String getCid() {
        return getAppInfo().getCid();
    }

    public void saveCid(String cid) {
        AppInfoModel infoModel = getAppInfo();
        infoModel.setCid(cid);
        save(infoModel);
    }
}
