package com.dianjiake.android.ui.evaluate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.data.bean.OrderServiceBean;
import com.dianjiake.android.ui.common.OrderViewType;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;
import com.dianjiake.android.view.widget.BaseViewHolder;
import com.dianjiake.android.view.widget.RatingBar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lfs on 2017/7/20.
 */

public class EvaluateAdapter extends BaseLoadMoreAdapter<OrderServiceBean> {
    EvaluateContract.Presenter presenter;


    public EvaluateAdapter(List<OrderServiceBean> items, EvaluateContract.Presenter presenter) {
        super(items);
        this.presenter = presenter;
    }

    @Override
    public void myOnBindViewHolder(BaseViewHolder holder, int position) {
        if (myGetItemViewType(position) == OrderViewType.NORMAL) {

        }
    }

    @Override
    public BaseViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EvaluateType.BUTTON) {
            return new SubmmitVH(UIUtil.inflate(R.layout.item_evaluate_button, parent), presenter);
        } else {
            return new ViewHolder(UIUtil.inflate(R.layout.item_evaluate, parent), presenter);

        }
    }

    @Override
    public int myGetItemViewType(int position) {
        return getItem(position).getViewType();
    }

    public static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.evaluate_logo)
        SimpleDraweeView evaluateLogo;
        @BindView(R.id.evaluate_name)
        TextView evaluateName;
        @BindView(R.id.evaluate_price)
        TextView evaluatePrice;
        @BindView(R.id.evaluate_unit)
        TextView evaluateUnit;
        @BindView(R.id.evaluate_old)
        TextView evaluateOld;
        @BindView(R.id.evaluate_service_holder)
        RelativeLayout evaluateServiceHolder;
        @BindView(R.id.evaluate_rating)
        RatingBar evaluateRating;
        @BindView(R.id.evaluate_desc)
        TextView evaluateDesc;
        @BindView(R.id.evaluate_input)
        EditText evaluateInput;
        @BindView(R.id.evaluate_count)
        TextView evaluateCount;
        EvaluateContract.Presenter presenter;

        public ViewHolder(View itemView, EvaluateContract.Presenter presenter) {
            super(itemView);
            this.presenter = presenter;
        }

        @Override
        public void destroy() {

        }
    }

    public static class SubmmitVH extends BaseViewHolder {
        @BindView(R.id.button)
        Button button;
        EvaluateContract.Presenter presenter;

        public SubmmitVH(View itemView, EvaluateContract.Presenter presenter) {
            super(itemView);
            this.presenter = presenter;
        }

        @Override
        public void destroy() {

        }
    }
}
