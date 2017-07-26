package com.dianjiake.android.ui.msg;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.data.bean.MsgBean;
import com.dianjiake.android.util.DateUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.BaseViewHolder;
import com.dianjiake.android.view.widget.MsgIconView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/26.
 */

public class MsgAdapter extends BaseLoadMoreAdapter<MsgBean> {
    MsgContract.Presenter p;

    public MsgAdapter(List<MsgBean> items, MsgContract.Presenter p) {
        super(items);
        this.p = p;
    }

    @Override
    public void myOnBindViewHolder(BaseViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.setItem(getItem(position));
    }

    @Override
    public BaseViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(UIUtil.inflate(R.layout.item_msg, parent), p);
    }

    @Override
    public int myGetItemViewType(int position) {
        return 0;
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.icon)
        MsgIconView icon;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.desc)
        TextView desc;
        MsgContract.Presenter p;
        MsgBean msgBean;

        public ViewHolder(View itemView, MsgContract.Presenter p) {
            super(itemView);
            this.p = p;
        }

        public void setItem(MsgBean msgBean) {
            this.msgBean = msgBean;
            title.setText(msgBean.getBiaoti());
            desc.setText(msgBean.getMiaoshu());
            time.setText(DateUtil.formatMDHM(msgBean.getAddtime()));
            icon.setShowRedIcon("0".equals(msgBean.getChakan()));
        }

        @OnClick(R.id.holder)
        void click(View v) {
            p.clickItem(msgBean, getAdapterPosition());
        }

        @Override
        public void destroy() {

        }
    }
}
