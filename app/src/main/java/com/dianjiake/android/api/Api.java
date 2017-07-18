package com.dianjiake.android.api;

import com.dianjiake.android.constant.Constant;
import com.dianjiake.android.data.bean.BaseBean;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.ShopCommentBean;
import com.dianjiake.android.data.bean.ShopDetailBean;
import com.dianjiake.android.data.bean.UserInfoBean;

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

    @GET(Constant.SHOP)
    Observable<BaseBean<ShopDetailBean>> shopDetail(
            @Query("bs") String bs,
            @Query("openid") String openid,
            @Query("shanghuid") String shopid,
            @Query("zuobiao") String coordinate
    );

    /**
     * 商户列表
     *
     * @param bs     {@link BSConstant#SERVICE_LIST}
     * @param shopid
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<ServiceBean>> serviceList(
            @Query("bs") String bs,
            @Query("shanghuid") String shopid,
            @Query("page") int page
    );

    /**
     * 商户员工列表
     *
     * @param bs     {@link BSConstant#SHOP_STAFF}
     * @param shopid
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<UserInfoBean>> shopStaff(
            @Query("bs") String bs,
            @Query("shanghuid") String shopid,
            @Query("page") int page
    );

    /**
     * 商户评论列表
     *
     * @param bs     {@link BSConstant#SHOP_STAFF}
     * @param shopid
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<ShopCommentBean>> shopComment(
            @Query("bs") String bs,
            @Query("shanghuid") String shopid,
            @Query("page") int page
    );

    /**
     * 服务员工列表
     *
     * @param bs     {@link BSConstant#SHOP_STAFF}
     * @param shopid
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<UserInfoBean>> serviceStaff(
            @Query("bs") String bs,
            @Query("shanghuid") String shopid,
            @Query("fuwuid") String serviceId,
            @Query("page") int page
    );


    /**
     * 员工可提供的服务列表
     *
     * @param bs     {@link BSConstant#STAFF_SERVICE}
     * @param shopid
     * @param page
     * @return
     */
    @GET(Constant.SHOP)
    Observable<BaseListBean<ServiceBean>> staffService(
            @Query("bs") String bs,
            @Query("shanghuid") String shopid,
            @Query("openid") String openId,
            @Query("page") int page
    );
}
