package com.dianjiake.android.ui.subscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.AndroidBug5497Workaround;
import com.dianjiake.android.data.bean.ServiceBean;
import com.dianjiake.android.data.bean.UserInfoBean;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.dialog.NormalProgressDialog;
import com.dianjiake.android.view.widget.ServiceItemView;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 2017/7/18.
 */

public class SubscribeActivity extends BaseTranslateActivity<SubscribePresenter> implements SubscribeContract.View,
        ServiceItemView.OnChooseClickListener {

    private static final String KEY_SERVICE = "key_service";
    private static final String KEY_STAFF = "key_staff";
    private static final int REQUEST_SERVICE = 23;
    private static final int REQUEST_STAFF = 34;


    public static Intent getStartIntent(ServiceBean serviceBean, UserInfoBean staff) {
        Intent intent = IntentUtil.getIntent(SubscribeActivity.class);
        intent.putExtra(KEY_SERVICE, serviceBean);
        intent.putExtra(KEY_STAFF, staff);
        return intent;
    }

    @BindView(R.id.other_service_holder)
    LinearLayout otherServiceHolder;
    @BindView(R.id.locaion_name)
    TextView locaionName;
    @BindView(R.id.location_holder)
    LinearLayout locationHolder;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.address_holder)
    LinearLayout addressHolder;
    @BindView(R.id.come_holder)
    LinearLayout comeHolder;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.button)
    Button button;
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

    NormalProgressDialog progressDialog;


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
        presenter.setDefaultValue((ServiceBean) getIntent().getParcelableExtra(KEY_SERVICE),
                (UserInfoBean) getIntent().getParcelableExtra(KEY_STAFF));
        addServiceItem();
    }

    @Override
    public SubscribePresenter getPresenter() {
        return new SubscribePresenter(this);
    }

    @OnClick(R.id.add_service)
    void clickAdd(View v) {
        addServiceItem();
    }

    void addServiceItem() {
        ServiceItemView serviceItemView = new ServiceItemView(this);
        serviceItemView.setListener(this);
        presenter.addServiceItem(serviceItemView);
        otherServiceHolder.addView(serviceItemView);
    }


    @Override
    public void onChooseServiceClick(View v) {
        startActivityForResult(ServiceStaffListActivity.getServiceIntent(presenter.getShopId(), presenter.getOpenId()), REQUEST_SERVICE + (int) v.getTag());
    }

    @Override
    public void onChooseStaffClick(View v) {
        startActivityForResult(ServiceStaffListActivity.getStaffIntent(presenter.getShopId(), presenter.getServiceId()), REQUEST_STAFF + (int) v.getTag());

    }
}
