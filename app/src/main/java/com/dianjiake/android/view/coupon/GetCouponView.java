package com.dianjiake.android.view.coupon;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dianjiake.android.R;
import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.BaseListBean;
import com.dianjiake.android.data.bean.CouponBean;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.coupon.common.CouponAdapterHelper;
import com.dianjiake.android.view.coupon.common.CouponScaleHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lfs on 2017/7/24.
 */

public class GetCouponView extends FrameLayout {
    RecyclerView rv;
    Adapter adapter;
    CompositeDisposable cd;
    LoginInfoModel loginInfo;
    CouponScaleHelper couponScaleHelper;

    public GetCouponView(@NonNull Context context) {
        super(context);
        init();
    }

    public GetCouponView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GetCouponView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    void init() {
        View view = inflate(getContext(), R.layout.get_coutpon_view, this);
        setClipChildren(false);
        couponScaleHelper = new CouponScaleHelper();
        rv = (RecyclerView) view.findViewById(R.id.coupon_rv);
        cd = new CompositeDisposable();
        adapter = new Adapter();
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(adapter);
        couponScaleHelper.attachToRecyclerView(rv);
        loginInfo = LoginInfoDBHelper.newInstance().getLoginInfo();
    }

    public void setShop(HomeShopBean shop) {
        if (shop != null) {
            cd.clear();
            Network.getInstance().shopCoupon(
                    BSConstant.SHOP_COUPON,
                    loginInfo.getOpenId(),
                    shop.getId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new Observer<BaseListBean<CouponBean>>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            cd.add(d);
                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull BaseListBean<CouponBean> couponList) {
                            if (!CheckEmptyUtil.isEmpty(couponList.getObj().getList())) {
                                adapter.setItems(couponList.getObj().getList());
                            }
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        cd.clear();
        super.onDetachedFromWindow();
    }

    public static class GetCouponAdapter extends PagerAdapter {

        private List<CouponBean> mItems;
        private HomeShopBean shopBean;

        public GetCouponAdapter() {
            mItems = new ArrayList<>();
        }

        public void setShopBean(HomeShopBean shopBean) {
            this.shopBean = shopBean;
        }

        public void setItems(List items) {
            mItems.clear();
            mItems.addAll(items);
            mItems.addAll(items);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mItems == null ? 0 : mItems.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CouponView couponView = (CouponView) container.findViewWithTag(position);
            if (couponView == null) {
                couponView = new CouponView(container.getContext());
                couponView.setGetVisible(true);
            }
            couponView.setCoupon(mItems.get(position), shopBean);
            couponView.setTag(position);
            container.addView(couponView);
            return couponView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mItems.size() > position) {
                container.removeView((View) object);
            }
        }

        @Override
        public float getPageWidth(int position) {
            return 0.8f;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private List<CouponBean> items = new ArrayList<>();
        private CouponAdapterHelper couponAdapterHelper = new CouponAdapterHelper();

        public void setItems(List<CouponBean> items) {
            this.items.clear();
            if (!CheckEmptyUtil.isEmpty(items)) {
                this.items.addAll(items);
                this.items.addAll(items);
                this.items.addAll(items);
                this.items.addAll(items);
                this.items.addAll(items);
                this.items.addAll(items);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = UIUtil.inflate(R.layout.item_coupon_view, parent);
            couponAdapterHelper.onCreateViewHolder(parent, view);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            couponAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
            holder.setItem(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CouponView couponView;
        public ViewHolder(View itemView) {
            super(itemView);
            couponView = (CouponView) itemView.findViewById(R.id.cv);
        }

        public void setItem(CouponBean couponBean) {
            couponView.setCoupon(couponBean, null);
        }
    }
}
