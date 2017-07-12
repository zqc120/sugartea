package com.dianjiake.android.ui.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.ui.main.HomeType;
import com.dianjiake.android.util.AMapUtil;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.FloatUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.LongUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.BaseViewHolder;
import com.dianjiake.android.view.widget.HomeFilterView;
import com.dianjiake.android.view.widget.StarView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/10.
 */

public class ShopListAdapter extends BaseLoadMoreAdapter<HomeShopBean> {
    public ShopListAdapter(List<HomeShopBean> items) {
        super(items);
    }

    @Override
    public void myOnBindViewHolder(BaseViewHolder holder, int position) {
        switch (myGetItemViewType(position)) {
            case HomeType.AD:
                break;
            case HomeType.FILTER:
                break;
            default:
                ViewHolder vh = (ViewHolder) holder;
                vh.setItem(getItem(position));
        }

    }

    @Override
    public BaseViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomeType.AD:
                return ADHolder.newInstance(parent);
            case HomeType.FILTER:
                return FilterHolder.newInstance(parent);
            default:
                return ViewHolder.newInstance(parent, this);
        }
    }

    @Override
    public int myGetItemViewType(int position) {
        return getItem(position).getViewType();
    }


    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.logo)
        SimpleDraweeView logo;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.star)
        StarView star;
        @BindView(R.id.promotion_desc)
        TextView promotionDesc;
        @BindView(R.id.promotion_holder)
        LinearLayout promotionHolder;
        @BindView(R.id.card_desc)
        TextView cardDesc;
        @BindView(R.id.card_holder)
        LinearLayout cardHolder;

        ShopListAdapter adapter;
        HomeShopBean item;

        public static ViewHolder newInstance(ViewGroup parent, ShopListAdapter adapter) {
            return new ViewHolder(UIUtil.inflate(R.layout.item_home_shop, parent), adapter);
        }

        private ViewHolder(View itemView, ShopListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
        }

        public void setItem(HomeShopBean item) {
            this.item = item;
            logo.setImageURI(FrescoUtil.getShopLogoUri(item.getLogo(), item.getCover()));
            name.setText(item.getMingcheng());
            desc.setVisibility(CheckEmptyUtil.isEmpty(item.getJianjie()) ? View.GONE : View.VISIBLE);
            desc.setText(item.getJianjie());
            distance.setText(AMapUtil.formatDistance(LongUtil.parseLong(item.getJuli())));
            star.setScore(FloatUtil.parseFloat(item.getPingfen()));

        }

        @OnClick(R.id.holder)
        void click(View v) {
            adapter.onClick(item, getAdapterPosition());
        }

        @Override
        public void destroy() {

        }
    }

    public static class ADHolder extends BaseViewHolder {

        public static ADHolder newInstance(ViewGroup parent) {
            return new ADHolder(UIUtil.inflate(R.layout.item_home_ad, parent));
        }

        private ADHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void destroy() {

        }
    }

    public static class FilterHolder extends BaseViewHolder {

        public static FilterHolder newInstance(ViewGroup parent) {
            return new FilterHolder(new HomeFilterView(parent.getContext()));
        }

        private FilterHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void destroy() {

        }
    }

}
