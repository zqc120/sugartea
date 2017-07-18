package com.dianjiake.android.ui.subscribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.AndroidBug5497Workaround;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lfs on 2017/7/18.
 */

public class SubscribeActivity extends BaseTranslateActivity<SubscribePresenter> implements SubscribeContract.View {
    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_icon_left)
    ImageView toolbarIconLeft;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_icon_right)
    ImageView toolbarIconRight;
    @BindView(R.id.add_service)
    LinearLayout addService;
    @BindView(R.id.time_name)
    TextView timeName;
    @BindView(R.id.time_holder)
    LinearLayout timeHolder;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.phone_holder)
    LinearLayout phoneHolder;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.subscribe_male)
    RadioButton subscribeMale;
    @BindView(R.id.subscribe_female)
    RadioButton subscribeFemale;
    @BindView(R.id.subscribe_gender)
    RadioGroup subscribeGender;
    @BindView(R.id.name_holder)
    LinearLayout nameHolder;

    @Override
    public void setPresenter(SubscribeContract.Presenter presenter) {

    }

    @Override
    public int provideContentView() {
        return R.layout.activity_subscribe;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(this);
        toolbarTitle.setText("快速预约");
    }

    @Override
    public SubscribePresenter getPresenter() {
        return null;
    }


}
