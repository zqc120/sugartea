package com.dianjiake.android.ui.searchlocation;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.dianjiake.android.util.ToastUtil;

/**
 * Created by lfs on 2017/7/11.
 */

public class SearchLocationPresenter implements SearchLocationContract.Presenter, PoiSearch.OnPoiSearchListener {
    SearchLocationContract.View view;
    PoiSearch poiSearch;

    public SearchLocationPresenter(SearchLocationContract.View view) {
        this.view = view;

        PoiSearch.Query query = new PoiSearch.Query("", "120302");
        poiSearch = new PoiSearch(view.getContext(), query);
        poiSearch.setOnPoiSearchListener(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        poiSearch.setOnPoiSearchListener(null);

    }

    @Override
    public void geoSearch(LatLng latLng) {
        LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
        poiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, 1000));
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        view.setItems(poiResult.getPois());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
