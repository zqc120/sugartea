package com.dianjiake.android.api;

import com.dianjiake.android.constant.Constant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.ServiceBean;

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
     * @param bs         {@link BSConstant#SHOP_LIST}
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

    /**
     * @param bs         {@link BSConstant#SEARCH_SHOP}
     * @param openid
     * @param keyword
     * @param order
     * @param coordinate
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<HomeShopBean>> searchShop(
            @Query("bs") String bs,
            @Query("openid") String openid,
            @Query("guanjianci") String keyword,
            @Query("paixu") int order,
            @Query("zuobiao") String coordinate,
            @Query("page") int page
    );

    /**
     * @param bs         {@link BSConstant#SEARCH_SERVICE}
     * @param openid
     * @param keyword
     * @param order
     * @param coordinate
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<ServiceBean>> searchService(
            @Query("bs") String bs,
            @Query("openid") String openid,
            @Query("guanjianci") String keyword,
            @Query("paixu") int order,
            @Query("zuobiao") String coordinate,
            @Query("page") int page
    );

}
