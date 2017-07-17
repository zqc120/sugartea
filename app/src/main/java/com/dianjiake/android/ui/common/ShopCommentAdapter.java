package com.dianjiake.android.ui.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.util.FloatUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntegerUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.BaseViewHolder;
import com.dianjiake.android.view.widget.StarView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lfs on 2017/7/17.
 */

public class ShopCommentAdapter extends BaseLoadMoreAdapter<UserInfoBean> {

    public ShopCommentAdapter(List<UserInfoBean> items) {
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


        @BindView(R.id.avatar)
        SimpleDraweeView avatar;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.star_view)
        StarView starView;
        @BindView(R.id.sail_count)
        TextView sailCount;

        public static ViewHolder newInstance(ViewGroup parent) {
            return new ViewHolder(UIUtil.inflate(R.layout.item_shop_staff, parent));
        }

        private ViewHolder(View itemView) {
            super(itemView);
        }

        public void setItem(UserInfoBean userInfo) {
            avatar.setImageURI(FrescoUtil.getOccupationAvatar(userInfo.getZhiyezhao()));
            name.setText(userInfo.getShanghunicheng());
            desc.setText(userInfo.getYuangongjianjie());
            starView.setScore(FloatUtil.parseFloat(userInfo.getFuwupingfen()));
            int sails = IntegerUtil.parseInt(userInfo.getJiedanshu());
            sailCount.setVisibility(sails > 0 ? View.VISIBLE : View.GONE);
            sailCount.setText("已约" + sails + "单");

        }

        @Override
        public void destroy() {

        }
    }
}
