package com.dianjiake.android.ui.searchlocation;

import android.content.Context;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseView;

import java.util.List;

/**
 * Created by lfs on 2017/7/11.
 */

public interface SearchLocationContract {
    interface View extends BaseView<Presenter> {
        Context getContext();

        void setItems(List<PoiItem> items);
    }

    interface Presenter extends BasePresenter {
        void geoSearch(LatLng latLng);
    }
}
