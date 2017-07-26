package com.dianjiake.android.data.db;

import com.dianjiake.android.data.bean.MsgBean;
import com.dianjiake.android.data.bean.MsgBeanDao;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.EventUtil;

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

    public void save(List<MsgBean> msgBeen) {
        getMsgDao().insertOrReplaceInTx(msgBeen);
        EventUtil.postCheckMsgUnread();
    }

    public void delete(MsgBean msgBean) {
        if (msgBean != null) {
            getMsgDao().delete(msgBean);
            EventUtil.postCheckMsgUnread();
        }
    }

    public boolean isUnread() {
        return !CheckEmptyUtil.isEmpty(getMsgDao().queryBuilder().list());
    }
}
