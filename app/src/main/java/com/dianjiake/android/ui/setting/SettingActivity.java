package com.dianjiake.android.ui.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/20.
 */

public class SettingActivity extends BaseTranslateActivity<SettingPresenter> implements SettingContract.View {
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
    @BindView(R.id.setting_call)
    LinearLayout settingCall;
    @BindView(R.id.setting_evaluate)
    LinearLayout settingEvaluate;
    @BindView(R.id.setting_log_out)
    Button settingLogOut;

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {

    }

    @Override
    public int provideContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public SettingPresenter getPresenter() {
        return null;
    }


    @OnClick(R.id.toolbar_icon_left)
    void clickBack(View v) {
        finish();
    }

    @OnClick(R.id.setting_call)
    void clickCall(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("确定呼叫：010-57206260")
                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01057206260"));
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @OnClick(R.id.setting_evaluate)
    void clickEvaluate(View v) {
        Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
