package com.dianjiake.android.ui.searchlocation;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.dianjiake.android.R;
import com.dianjiake.android.base.App;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.util.ToastUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by lfs on 2017/7/11.
 */

public class SearchLocationActivity extends BaseTranslateActivity<SearchLocationContract.Presenter> implements SearchLocationContract.View {


    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_input)
    EditText toolbarInput;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    AMap map;
    Marker marker;
    String cityCode;
    SearchResultAdapter searchResultAdapter;
    boolean searchFocus;//搜索框是否获取焦点
    InputMethodManager imm;

    int markerPositionX, markerPositionY;

    @Override
    public void setPresenter(SearchLocationContract.Presenter presenter) {

    }

    @Override
    public int provideContentView() {
        return R.layout.activity_search_location;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        imm = (InputMethodManager) App.getInstance().getSystemService(INPUT_METHOD_SERVICE);
        mapView.onCreate(savedInstanceState);
        map = mapView.getMap();
        intiMapView();
        markerPositionX = UIUtil.getScreenWidth() / 2;
        markerPositionY = UIUtil.getScreenWidth() / 5;
        searchResultAdapter = new SearchResultAdapter();
        listView.setAdapter(searchResultAdapter);
        toolbarInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ToastUtil.showShortToast(hasFocus + "");
            }
        });
    }

    private void intiMapView() {
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.myLocationType((MyLocationStyle.LOCATION_TYPE_SHOW));
        locationStyle.showMyLocation(true);
        locationStyle.radiusFillColor(UIUtil.getColor(R.color.map_radius_fill));
        locationStyle.strokeColor(UIUtil.getColor(R.color.map_radius_stroke));
        locationStyle.strokeWidth(3);
        map.setMyLocationStyle(locationStyle);
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 16, 0, 0)));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.draggable(false);
                marker = map.addMarker(markerOptions);
                marker.setPositionByPixels(markerPositionX, markerPositionY);
            }
        });

        map.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                if (marker != null) {
                    Timber.d("position :" + marker.getPosition().toString());
                    presenter.geoSearch(marker.getPosition());
                }
            }
        });

    }


    @Override
    public SearchLocationContract.Presenter getPresenter() {
        return new SearchLocationPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        imm = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (searchFocus) {
            setSearchFocus(false);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.toolbar_right)
    void clickCancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.toolbar_title)
    void clickSearch(View v) {
        setSearchFocus(true);
    }

    void setSearchFocus(boolean focus) {
        searchFocus = focus;
        if (focus) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(toolbarInput.getWindowToken(), 0);
        }

        toolbarTitle.setVisibility(focus ? View.GONE : View.VISIBLE);
        toolbarInput.setVisibility(focus ? View.VISIBLE : View.GONE);
        if (focus) {
            toolbarInput.requestFocus();
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setItems(List<PoiItem> items) {
        searchResultAdapter.setItems(items);
    }


}
