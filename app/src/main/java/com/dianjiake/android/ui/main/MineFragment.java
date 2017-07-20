package com.dianjiake.android.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseFragment;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.ui.login.CompleteInfoActivity;
import com.dianjiake.android.ui.setting.SettingActivity;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.dialog.NormalAlertDialog;
import com.dianjiake.android.view.widget.ToolbarSpaceView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/19.
 */

public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    @BindView(R.id.space)
    ToolbarSpaceView space;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_icon_left)
    ImageView toolbarIconLeft;
    @BindView(R.id.toolbar_icon_right)
    ImageView toolbarIconRight;
    @BindView(R.id.avatar)
    SimpleDraweeView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.mine_order_text)
    TextView mineOrderText;
    @BindView(R.id.mine_order)
    LinearLayout mineOrder;
    @BindView(R.id.mine_collection_text)
    TextView mineCollectionText;
    @BindView(R.id.mine_collection)
    LinearLayout mineCollection;
    @BindView(R.id.mine_cards_text)
    TextView mineCardsText;
    @BindView(R.id.mine_cards)
    LinearLayout mineCards;
    @BindView(R.id.mine_vip_text)
    TextView mineVipText;
    @BindView(R.id.mine_vip)
    LinearLayout mineVip;
    @BindView(R.id.mine_call)
    LinearLayout mineCall;
    @BindView(R.id.item_holder)
    LinearLayout itemHolder;

    @Override
    public void setPresenter(MineContract.Presenter presenter) {

    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MineContract.Presenter getPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public void setViews(UserInfoBean userInfoBean) {
        setAvatar(userInfoBean.getAvatar());
        setPhone(userInfoBean.getPhone());
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar.setImageURI(FrescoUtil.getAvatarUri(avatar));
    }

    @Override
    public void setPhone(String phone) {
        name.setText(formatPhone(phone));
    }

    public String formatPhone(String phone) {
        String result = "";
        try {
            result = phone.substring(0, 3) + "****" + phone.substring(7);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            presenter.load();
        }
    }

    @OnClick(R.id.mine_call)
    void clickCall(View v) {
        NormalAlertDialog dialog = NormalAlertDialog.newInstance("确定呼叫：010-57206260？", true, true);
        dialog.setOnButtonClick(new NormalAlertDialog.OnButtonClick() {
            @Override
            public void click(int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01057206260"));
                    startActivity(intent);
                }
            }
        });
        dialog.showDialog(getFragmentManager(), "call");
    }

    @OnClick(R.id.avatar)
    void clickAvatar(View v) {
        startActivity(CompleteInfoActivity.getEditIntent());
    }

    @OnClick(R.id.toolbar_icon_left)
    void clickCancel(View v) {
        startActivity(IntentUtil.getIntent(SettingActivity.class));
    }
}
