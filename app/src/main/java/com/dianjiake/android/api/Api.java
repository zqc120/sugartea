package com.dianjiake.android.api;

import com.dianjiake.android.constant.Constant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.HomeShopBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lfs on 2017/6/6.
 */

public interface Api {

    /**
     * 首页店铺列表
     *
     * @param bs         {@link com.dianjiake.android.constant.BSConstant#SHOP_LIST}
     * @param openid
     * @param order      （0默认 2距离 1评分）
     * @param coordinate
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<HomeShopBean>> homeShop(
            @Query("bs") String bs,
            @Query("openid") String openid,
            @Query("paixu") int order,
            @Query("zuobiao") String coordinate,
            @Query("page") int page
    );

}
