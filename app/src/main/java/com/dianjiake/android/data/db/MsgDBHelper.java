package com.dianjiake.android.data.db;

import com.dianjiake.android.data.bean.MsgBean;
import com.dianjiake.android.data.bean.MsgBeanDao;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.EventUtil;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.List;


/**
 * Created by lfs on 2017/7/26.
 */

public class MsgDBHelper {

    public static MsgDBHelper newInstance() {
        return new MsgDBHelper();
    }

    private MsgDBHelper() {
    }

    public MsgBeanDao getMsgDao() {
        return DBManager.getInstance().getDaoSession().getMsgBeanDao();
    }

    public void save(List<MsgBean> msgBean) {
        if (msgBean != null) {
            getMsgDao().insertOrReplaceInTx(msgBean);
            EventUtil.postCheckMsgUnread();
        }
    }

    public void save(MsgBean msgBean) {
        if (msgBean != null) {
            getMsgDao().insertOrReplace(msgBean);
            EventUtil.postCheckMsgUnread();
        }
    }

    public void delete(MsgBean msgBean) {
        if (msgBean != null) {
            DeleteQuery<MsgBean> deleteQuery= getMsgDao().queryBuilder()
                    .where(MsgBeanDao.Properties.Id.eq(msgBean.getId()))
                    .buildDelete();
            deleteQuery.executeDeleteWithoutDetachingEntities();
            EventUtil.postCheckMsgUnread();
        }
    }

    public boolean isUnread() {
        return !CheckEmptyUtil.isEmpty(getMsgDao().queryBuilder().list());
    }
}
