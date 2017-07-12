package com.dianjiake.android.data.db;

import com.dianjiake.android.data.model.SearchHistoryModel;
import com.dianjiake.android.data.model.SearchHistoryModelDao;
import com.dianjiake.android.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfs on 2017/7/12.
 */

public class SearchHistoryHelper {
    private static final int TYPE_LOCATION = 1;

    public static SearchHistoryHelper newInstance() {
        return new SearchHistoryHelper();
    }

    public SearchHistoryModelDao getDao() {
        return DBManager.getInstance().getDaoSession().getSearchHistoryModelDao();
    }

    public List<SearchHistoryModel> getSearchLocationHistory() {
        List<SearchHistoryModel> list = getDao().queryBuilder().where(SearchHistoryModelDao.Properties.Type.eq(TYPE_LOCATION))
                .orderDesc(SearchHistoryModelDao.Properties.Update_at)
                .list();
        if (CheckEmptyUtil.isEmpty(list)) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void addSearchLocationEntity(String search) {
        SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
        searchHistoryModel.setSearch(search);
        searchHistoryModel.setUpdate_at(System.currentTimeMillis());
        searchHistoryModel.setType(TYPE_LOCATION);
        getDao().insertOrReplace(searchHistoryModel);
    }
}
