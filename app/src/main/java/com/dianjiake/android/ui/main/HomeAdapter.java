package com.dianjiake.android.ui.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.data.bean.HomeShopBean;
import com.dianjiake.android.util.AMapUtil;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.FloatUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.LongUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.BaseViewHolder;
import com.dianjiake.android.view.widget.StarView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lfs on 2017/7/10.
 */

public class HomeAdapter extends BaseLoadMoreAdapter<HomeShopBean> {
    public HomeAdapter(List<HomeShopBean> items) {
        super(items);
    }

    @Override
    public void myOnBindViewHolder(BaseViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.setItem(getItem(position));

    }

    @Override
    public BaseViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.newInstance(parent);
    }

    @Override
    public int myGetItemViewType(int position) {
        return 0;
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

        public static ViewHolder newInstance(ViewGroup parent) {
            return new ViewHolder(UIUtil.inflate(R.layout.item_home_shop, parent));
        }

        private ViewHolder(View itemView) {
            super(itemView);
        }

        public void setItem(HomeShopBean item) {
            logo.setImageURI(FrescoUtil.getShopLogoUri(item.getLogo(), item.getCover()));
            name.setText(item.getMingcheng());
            desc.setVisibility(CheckEmptyUtil.isEmpty(item.getJianjie()) ? View.GONE : View.VISIBLE);
            desc.setText(item.getJianjie());
            distance.setText(AMapUtil.formatDistance(LongUtil.parseLong(item.getJuli())));
            star.setScore(FloatUtil.parseFloat(item.getPingfen()));

        }

        @Override
        public void destroy() {

        }
    }

}
