package com.dianjiake.android.ui.orderdetail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.data.bean.OrderBean;
import com.dianjiake.android.data.bean.OrderGoodBean;
import com.dianjiake.android.data.bean.OrderServiceBean;
import com.dianjiake.android.ui.common.OrderStatus;
import com.dianjiake.android.util.CheckEmptyUtil;
import com.dianjiake.android.util.DateUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.util.TableRowUtil;
import com.dianjiake.android.util.UIUtil;
import com.dianjiake.android.view.dialog.NormalAlertDialog;
import com.dianjiake.android.view.dialog.NormalProgressDialog;
import com.dianjiake.android.view.widget.ToolbarSpaceView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/20.
 */

public class OrderDetailActivity extends BaseTranslateActivity<OrderDetailPresenter> implements OrderDetailContract.View {

    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_icon_left)
    ImageView toolbarIconLeft;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_icon_right)
    ImageView toolbarIconRight;
    @BindView(R.id.toolbar_divider)
    ImageView toolbarDivider;
    @BindView(R.id.toolbar_holder)
    ConstraintLayout toolbarHolder;
    @BindView(R.id.detail_logo)
    SimpleDraweeView detailLogo;
    @BindView(R.id.detail_status)
    TextView detailStatus;
    @BindView(R.id.detail_desc)
    TextView detailDesc;
    @BindView(R.id.detail_button1)
    Button detailButton1;
    @BindView(R.id.detail_button2)
    Button detailButton2;
    @BindView(R.id.logo)
    SimpleDraweeView logo;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.logo_divider)
    ImageView logoDivider;
    @BindView(R.id.table_layout)
    TableLayout tableLayout;
    @BindView(R.id.table_divider)
    ImageView tableDivider;
    @BindView(R.id.sub_title)
    TextView subTitle;
    @BindView(R.id.sub_divider)
    ImageView subDivider;
    @BindView(R.id.sub_table)
    TableLayout subTable;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    int orderStatus;
    OrderBean orderBean;
    NormalProgressDialog cancelPD;

    public static Intent getStartIntent(OrderBean order) {
        Intent intent = IntentUtil.getIntent(OrderDetailActivity.class);
        intent.putExtra("order", order);
        return intent;
    }

    @Override
    public void setPresenter(OrderDetailContract.Presenter presenter) {

    }

    @Override
    public int provideContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        orderBean = getIntent().getParcelableExtra("order");
        setView(orderBean);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        tableDivider.setVisibility(View.GONE);
    }

    @Override
    public OrderDetailPresenter getPresenter() {
        return new OrderDetailPresenter(this);
    }

    @OnClick(R.id.toolbar_icon_left)
    void clickBack(View v) {
        finish();
    }

    @Override
    public void setView(OrderBean item) {
        if (item.getDianpu() != null) {
            logo.setImageURI(FrescoUtil.getShopLogoUri(item.getDianpu().getLogo(), item.getDianpu().getCover()));
            detailLogo.setImageURI(FrescoUtil.getShopLogoUri(item.getDianpu().getLogo(), item.getDianpu().getCover()));
            name.setText(item.getDianpu().getMingcheng());
        }
        orderStatus = OrderStatus.getStatus(item.getStatus());
        detailStatus.setText(OrderStatus.getStatusText(item.getStatus()));
        detailDesc.setText(OrderStatus.getStatusDescText(item.getStatus()));
        detailButton2.setVisibility("1".equals(item.getShifoupinglun()) ? View.GONE : View.VISIBLE);

        tableLayout.removeAllViews();

        if (!CheckEmptyUtil.isEmpty(item.getDingdanfuwu())) {
            for (int i = 0; i < item.getDingdanfuwu().size(); i++) {
                OrderServiceBean orderService = item.getDingdanfuwu().get(i);
                TableRow tableRow = new TableRow(this);
                tableRow.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
                if (i < 3) {
                    tableRow.addView(TableRowUtil.getServiceTitleText(orderService.getFuwumingcheng(), this));
                    tableRow.addView(TableRowUtil.getServiceEndText("￥" + orderService.getDanjia(), this));
                } else if (i == 3) {
                    tableRow.addView(TableRowUtil.getCountTitleText("...", this));
                } else {
                    break;
                }
                tableLayout.addView(tableRow);
            }
        }


        if (!CheckEmptyUtil.isEmpty(item.getDingdanshangpin())) {
            for (int i = 0; i < item.getDingdanshangpin().size(); i++) {
                OrderGoodBean orderGood = item.getDingdanshangpin().get(i);
                TableRow tableRow = new TableRow(this);
                tableRow.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
                if (tableLayout.getChildCount() == 3) {
                    tableRow.addView(TableRowUtil.getCountTitleText("...", this));

                } else if (tableLayout.getChildCount() < 3) {
                    tableRow.addView(TableRowUtil.getServiceTitleText(orderGood.getMingcheng(), this));
                    tableRow.addView(TableRowUtil.getServiceEndText("￥" + orderGood.getDanjia(), this));
                } else {
                    break;
                }
                tableLayout.addView(tableRow);
            }
        }

        if (!CheckEmptyUtil.isEmpty(item.getDingdanfuwu())) {
            TableRow countRow = new TableRow(this);
            countRow.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
            countRow.addView(TableRowUtil.getCountTitleText(item.getDingdanfuwu().size() + "个项目", this));
            countRow.addView(TableRowUtil.getCountEndText("合计：￥" + item.getYingfujine(), this));
            tableLayout.addView(countRow);
        }


        TableRow finalPay = new TableRow(this);
        finalPay.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
        finalPay.addView(TableRowUtil.getVipTitleText(item.getDengjimingcheng() + item.getFuwuzhekou() + "折", this));
        finalPay.addView(TableRowUtil.getVipEndText("会员价格：￥" + item.getShifujine(), this));
        tableLayout.addView(finalPay);

        setButtonText();

        subTable.removeAllViews();
        TableRow serviceTime = new TableRow(this);
        serviceTime.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
        serviceTime.addView(TableRowUtil.getSubText("服务时间：", this));
        serviceTime.addView(TableRowUtil.getSubText(DateUtil.formatYMDHM(item.getFuwushijian()), this));
        subTable.addView(serviceTime);

        TableRow info = new TableRow(this);
        info.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
        info.addView(TableRowUtil.getSubText("客户信息：", this));
        info.addView(TableRowUtil.getSubText(item.getName(), this));
        subTable.addView(info);

        TableRow addTime = new TableRow(this);
        addTime.setPadding(0, 0, 0, UIUtil.getDimensionPixelSize(R.dimen.base_size3));
        addTime.addView(TableRowUtil.getSubText("下单时间：", this));
        addTime.addView(TableRowUtil.getSubText(DateUtil.formatYMDHM(item.getAddtime()), this));
        subTable.addView(addTime);
    }

    @Override
    public void showCancelPD() {
        if (cancelPD == null) {
            cancelPD = NormalProgressDialog.newInstance("正在取消预约，请稍候...");
        }
        cancelPD.showDialog(getFragmentManager(), "cancel");
    }

    @Override
    public void dismissCancelPD() {
        if (cancelPD != null) {
            cancelPD.dismissAllowingStateLoss();
        }
    }

    @Override
    public Context provideContext() {
        return this;
    }


    void setButtonText() {
        switch (orderStatus) {
            case OrderStatus.NO_CONFIRM:
            case OrderStatus.CONFIRM:
            case OrderStatus.RUNNING:
                detailButton1.setText("取消预约");
                detailButton2.setText("联系商家");
                break;
            case OrderStatus.COMPLETE:
                detailButton1.setText("再来一单");
                detailButton2.setText("评价服务");
                break;
            case OrderStatus.CANCEL:
                detailButton1.setText("再来一单");
                detailButton2.setText("联系商家");
                break;
        }
    }

    void cancelOrder() {
        NormalAlertDialog alertDialog = NormalAlertDialog.newInstance("确定要取消预约？", true, true);
        alertDialog.setOnButtonClick(new NormalAlertDialog.OnButtonClick() {
            @Override
            public void click(int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    presenter.cancelOrder();
                }
            }
        });
        alertDialog.showDialog(getFragmentManager(), "cancel");
    }

    @OnClick(R.id.button1)
    void clickButton1(View v) {
        switch (orderStatus) {
            case OrderStatus.NO_CONFIRM:
            case OrderStatus.CONFIRM:
            case OrderStatus.RUNNING:
                presenter.cancelOrder();
                break;
            case OrderStatus.COMPLETE:
            case OrderStatus.CANCEL:
                presenter.reSub();
                break;
        }
    }

    @OnClick(R.id.button2)
    void clickButton2(View v) {
        switch (orderStatus) {
            case OrderStatus.NO_CONFIRM:
            case OrderStatus.CONFIRM:
            case OrderStatus.RUNNING:
            case OrderStatus.CANCEL:
                presenter.call();
                break;
            case OrderStatus.COMPLETE:
                presenter.evaluate();
                break;

        }
    }
}
