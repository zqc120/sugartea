package com.dianjiake.android.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.api.Network;
import com.dianjiake.android.base.BasePresenter;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.custom.MyTextWatcher;
import com.dianjiake.android.data.bean.OccupationBean;
import com.dianjiake.android.data.db.LoginInfoDBHelper;
import com.dianjiake.android.data.model.LoginInfoModel;
import com.dianjiake.android.util.DateUtil;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.view.dialog.NormalProgressDialog;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 完善资料
 * Created by lfs on 17/2/17.
 */
@Deprecated
public class CompleteInfoActivity {
//    private static final String MALE = "1";
//    private static final String FEMALE = "2";
//    private static final int REQUEST_CODE_PHOTO = 121;
//    private static final int REQUEST_CODE_MICRODISTRICT = 131;
//    private static final String TYPE_EDIT = "edit";
//    private static final String KEY_NICKNAME = "nickname";
//    private static final String KEY_AVATAR = "avatar";
//    private static final String KEY_OPENID = "openId";
//
//    @BindView(R.id.toolbar_icon_left)
//    ImageView mToolbarBack;
//    @BindView(R.id.toolbar_title)
//    TextView mToolbarTitle;
//    @BindView(R.id.toolbar_divider)
//    View mToolbarDivider;
//    @BindView(R.id.complete_info_nickname)
//    EditText mNickname;
//    @BindView(R.id.complete_info_gender_text)
//    TextView mGenderText;
//    @BindView(R.id.complete_info_gender)
//    LinearLayout mGender;
//    @BindView(R.id.complete_info_age_text)
//    TextView mAgeText;
//    @BindView(R.id.complete_info_age)
//    LinearLayout mAge;
//    @BindView(R.id.complete_info_occupation_text)
//    TextView mOccupationText;
//    @BindView(R.id.complete_info_occupation)
//    LinearLayout mOccupation;
//    @BindView(R.id.complete_info_microdistrict_text)
//    TextView mMicrodistrictText;
//    @BindView(R.id.complete_info_microdistrict)
//    LinearLayout mMicrodistrict;
//    @BindView(R.id.complete_info_avatar)
//    SimpleDraweeView mAvatar;
//    @BindView(R.id.complete_info_commit)
//    Button mButtonCommit;
//
//    private NormalProgressDialog mCommitDialog;
//
//    private NormalProgressDialog mGetDialog;
//    private NormalProgressDialog mGetUserInfoDialog;
//    private String mFormAvatar;
//
//    private String mFormNickname;
//    private String mFormGender = MALE;
//    private String mFormOccupation;
//    private String mFormAge;
//
//    MicrodistrictItemBean mSelectMico;//已选择小区
//
//    private String mOpenId;
//    LoginInfoDBHelper loginInfoDBHelper;
//
//    public static Intent getStartIntent(String openId, String avatar, String nickname) {
//        Intent intent = IntentUtil.getIntent(CompleteInfoActivity.class);
//        intent.putExtra(KEY_OPENID, openId);
//        intent.putExtra(KEY_AVATAR, avatar);
//        intent.putExtra(KEY_NICKNAME, nickname);
//        return intent;
//    }
//
//    public static Intent getEditIntent() {
//        Intent intent = IntentUtil.getIntent(CompleteInfoActivity.class);
//        intent.setType(TYPE_EDIT);
//        return intent;
//    }
//
//    TextWatcher mNicknameWathcer = new MyTextWatcher() {
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            mFormNickname = String.valueOf(s);
//            setButtonStatus();
//        }
//    };
//
//    private List<OccupationBean> mOccupations;
//
//
//    @Override
//    public int provideContentView() {
//        return R.layout.activity_complete_info;
//    }
//
//    @Override
//    public void create(@Nullable Bundle savedInstanceState) {
//        loginInfoDBHelper = LoginInfoDBHelper.newInstance();
//
//        if (TextUtils.isEmpty(getIntent().getStringExtra("openId"))) {
//            mOpenId = loginInfoDBHelper.getLoginInfo().getOpenId();
//        } else {
//            mOpenId = getIntent().getStringExtra("openId");
//        }
//        mSelectMico = new MicrodistrictItemBean();
//
//        if (TYPE_EDIT.equals(getIntent().getType())) {
//            LoginInfoModel loginInfoModel = loginInfoDBHelper.getLoginInfo();
//            mToolbarTitle.setText("个人信息");
//            mButtonCommit.setText("保存");
//            mAvatar.setImageURI(FrescoUtil.getAvatarUri(loginInfoModel.getAvatar()));
//            mFormNickname = loginInfoModel.getNickname();
//            mFormGender = loginInfoModel.getSex();
//            mFormOccupation = loginInfoModel.getOccupationId();
//            mFormAge = loginInfoModel.getBirthday();
//            mSelectMico.setId(loginInfoModel.getCommunityId());
//            mSelectMico.setMingcheng(loginInfoModel.getCommunityName());
//            mFormAvatar = userInfoSP.getAvatar();
//
//            mNickname.setText(loginInfoModel.getNickname());
//            mGenderText.setText("1".equals(loginInfoModel.getSex()) ? "男" : "女");
//            mOccupationText.setText(loginInfoModel.getOccupation());
//            mAgeText.setText(DateUtil.getAge(LongUtil.parseLong(loginInfoModel.getBirthday())) + "");
//            mMicrodistrictText.setText(loginInfoModel.getCommunityName());
//        } else {
//            mToolbarTitle.setText("完善资料");
//            mFormNickname = getIntent().getStringExtra(KEY_NICKNAME);
//            ImageUtil.loadAvatar(getIntent().getStringExtra(KEY_AVATAR), mAvatar);
//            mFormAvatar = getIntent().getStringExtra(KEY_AVATAR);
//            getUserInfo();
//        }
//
//        if (!TextUtils.isEmpty(mNickname.getText())) {
//            mNickname.setSelection(mNickname.length());
//        }
//
//        mNickname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
//        mOccupations = new ArrayList<>();
//        mNickname.addTextChangedListener(mNicknameWathcer);
//        getOccupation();
//        initImagePicker();
//        setButtonStatus();
//    }
//
//    @Override
//    public BasePresenter getPresenter() {
//        return null;
//    }
//
//    void initImagePicker() {
//        ImagePicker imagePicker = ImagePicker.getInstance();
//        imagePicker.setMultiMode(false);
//        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
//        imagePicker.setShowCamera(true);                      //显示拍照按钮
//        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
//        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);
//    }
//
//    @OnClick(R.id.toolbar_back)
//    void pressBack(View v) {
//        onBackPressed();
//    }
//
//    @OnClick(R.id.complete_info_avatar)
//    void pressAvatar(View v) {
//        Intent intent = new Intent(this, ImageGridActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_PHOTO);
//    }
//
//
//    @OnClick(R.id.complete_info_gender)
//    void pressGender(View v) {
//        chooseGender();
//    }
//
//    @OnClick(R.id.complete_info_age)
//    void pressAge(View v) {
//        chooseDate();
//    }
//
//    @OnClick(R.id.complete_info_occupation)
//    void pressOccupation(View v) {
//        chooseOccupation();
//    }
//
//    @OnClick(R.id.complete_info_microdistrict)
//    void pressMicrodistrict(View v) {
//        Intent intent = new Intent(this, SearchMicroActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_MICRODISTRICT);
//    }
//
//    void chooseGender() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("请选择性别")
//                .setItems(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mGenderText.setText(which == 0 ? "男" : "女");
//                        mFormGender = which == 0 ? MALE : FEMALE;
//                        setButtonStatus();
//                    }
//                });
//        alertDialog.show();
//    }
//
//    void chooseOccupation() {
//        if (ListUtil.isEmpty(mOccupations)) {
//            ToastUtil.showShortToast("职业列表正在加载，请稍候...");
//            return;
//        }
//
//        String[] os = new String[mOccupations.size()];
//        for (int i = 0; i < mOccupations.size(); i++) {
//            os[i] = mOccupations.get(i).getMname();
//        }
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("请选择职业")
//                .setItems(os, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mFormOccupation = mOccupations.get(which).getId();
//                        mOccupationText.setText(mOccupations.get(which).getMname());
//                        setButtonStatus();
//                    }
//                });
//        alertDialog.show();
//
//    }
//
//    void chooseDate() {
//        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance("请选择生日");
//        datePickerDialog.showDialog(getFragmentManager(), null);
//        datePickerDialog.setOnPickListener(new DatePickerDialog.OnPickListener() {
//            @Override
//            public void onPick(long timestamp) {
//                if (timestamp >= System.currentTimeMillis()) {
//                    ToastUtil.showShortToast("日期选择有误");
//                    return;
//                }
//                mAgeText.setText(DateUtil.getAge(timestamp) + "");
//
//                mFormAge = String.valueOf(timestamp / 1000);
//                setButtonStatus();
//            }
//        });
//    }
//
//    void getOccupation() {
//        mNetwork.getOccupation(new Callback<OccupationBean>() {
//            @Override
//            public void onResponse(Call<OccupationBean> call, Response<OccupationBean> response) {
//                if (response.body() != null && response.body().getCode() == 200) {
//                    mOccupations.clear();
//                    mOccupations.addAll(response.body().getObj().getContent());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OccupationBean> call, Throwable t) {
//
//            }
//        });
//    }
//
//    @OnClick(R.id.complete_info_commit)
//    void pressCommit(View v) {
//        if (TextUtils.isEmpty(mFormAvatar) && !TYPE_EDIT.equals(getIntent().getType())) {
//            ToastUtil.showShortToast("请添加头像");
//        } else if (TextUtils.isEmpty(mFormNickname)) {
//            ToastUtil.showShortToast("请填写昵称");
//        } else if (TextUtils.isEmpty(mFormAge)) {
//            ToastUtil.showShortToast("请完善年龄信息");
//        } else if (TextUtils.isEmpty(mFormOccupation)) {
//            ToastUtil.showShortToast("请选择职业");
//        } else if (mSelectMico == null) {
//            ToastUtil.showShortToast("请选择小区");
//        } else {
//            commit();
//        }
//    }
//
//    void setButtonStatus() {
//        mButtonCommit.setTextColor(checkButtonStatus() ? UIUtil.getColor(R.color.white) : UIUtil.getColor(R.color.text_white_thirdly));
//    }
//
//    boolean checkButtonStatus() {
//        if (TextUtils.isEmpty(mFormAvatar) && !TYPE_EDIT.equals(getIntent().getType())) {
//            return false;
//        } else if (TextUtils.isEmpty(mFormNickname)) {
//            return false;
//        } else if (TextUtils.isEmpty(mFormAge)) {
//            return false;
//        } else if (TextUtils.isEmpty(mFormOccupation)) {
//            return false;
//        } else if (mSelectMico == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    void uploadAvatar() {
//        showCommitDialog();
//        UploadAvatarParams params = new UploadAvatarParams();
//        params.setAvatar(mFormAvatar);
//        params.setType("1");
//        params.setOpenId(mOpenId);
//        mNetwork.uploadAvatar(params.getRequestParams(), new Callback<BaseBean>() {
//            @Override
//            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
//                dismissCommitDialog();
//                if (response.body().getCode() == 200) {
//                    ToastUtil.showShortToast("头像上传成功");
//                } else {
//                    ToastUtil.showShortToast("头像上传失败，请重新选择头像并上传");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseBean> call, Throwable t) {
//                if (!call.isCanceled()) {
//                    dismissCommitDialog();
//                    ToastUtil.showShortToast("头像上传失败，请重新选择头像并上传");
//                }
//            }
//        });
//    }
//
//    void commit() {
//        showCommitDialog();
//        mNetwork.editUserInfo(mOpenId, mFormNickname, mFormGender, mFormAge, mSelectMico.getMingcheng(),
//                mFormOccupation, mSelectMico.getId(), new Callback<UserInfoBean>() {
//                    @Override
//                    public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
//                        dismissCommitDialog();
//                        if (response.body() != null) {
//                            if (response.body().getCode() == 200) {
//                                ToastUtil.showShortToast("保存成功");
//                                UserInfoBean bean = response.body();
//                                bean.getObj().setOpenid(mOpenId);
//                                bean.getObj().getUser().setOpenid(mOpenId);
//                                UserInfoSP.newInstance().saveUserInfo(bean);
//                                UserInfoSP.newInstance().saveSelectLocation(mSelectMico.getId(), mSelectMico.getMingcheng(), mSelectMico.getWeizhi());
//                                EventBusUtil.postSelectCommunity(mSelectMico.getId(), mSelectMico.getMingcheng(), mSelectMico.getWeizhi());
//
//                                if (TYPE_EDIT.equals(getIntent())) {
//                                } else {
//                                    LoginActivitiesManger.newInstance().completeInfoSuccess();
//                                }
//                                finish();
//                            } else {
//                                ToastUtil.showShortToast("资料提交失败Code" + response.body().getCode());
//                            }
//                        } else {
//                            ToastUtil.showShortToast("资料提交失败");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserInfoBean> call, Throwable t) {
//                        if (!call.isCanceled()) {
//                            dismissCommitDialog();
//                        }
//                    }
//                });
//    }
//
//    void getUserInfo() {
//        showGetDialog();
//        Network.getInstance().getUserInfo(mOpenId, new Callback<UserInfoBean>() {
//            @Override
//            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
//
//                dismissGetDialog();
//                setButtonStatus();
//                if (response.body() != null && response.body().getCode() == 200) {
//                    BaseUserBean userBean = response.body().getObj().getUser();
//                    ImageUtil.loadAvatar(BaseInfo.AVATAR_URL + userBean.getAvatar(), mAvatar);
//                    mFormNickname = userBean.getNickname();
//                    mFormGender = userBean.getSex() + "";
//                    mFormOccupation = userBean.getZhiyeid()+"";
//                    mFormAge = userBean.getBirthday();
//                    mSelectMico.setId(userBean.getShequid()+"");
//                    mSelectMico.setMingcheng(userBean.getLocation());
//                    mFormAvatar = userBean.getAvatar();
//
//                    mNickname.setText(userBean.getNickname());
//                    mGenderText.setText(1 == userBean.getSex() ? "男" : "女");
//                    mOccupationText.setText(userBean.getProfession());
//                    mAgeText.setText(DateUtil.getAge(LongUtil.parseLong(userBean.getBirthday())) + "");
//                    mMicrodistrictText.setText(userBean.getLocation());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<UserInfoBean> call, Throwable t) {
//                if (!call.isCanceled()) {
//                    dismissGetDialog();
//                }
//            }
//        });
//    }
//
//    void showCommitDialog() {
//        if (mCommitDialog == null) {
//            mCommitDialog = BaseProgressDialog.newInstance("正在提交,请稍候...");
//        }
//        mCommitDialog.showDialog(getFragmentManager(), "show");
//    }
//
//    void dismissCommitDialog() {
//        if (mCommitDialog != null) {
//            mCommitDialog.dismissAllowingStateLoss();
//        }
//    }
//
//    void showGetDialog() {
//        if (mGetDialog == null) {
//            mGetDialog = BaseProgressDialog.newInstance("正在获取用户资料，请稍候...");
//        }
//        mGetDialog.showDialog(getFragmentManager(), "show");
//    }
//
//    void dismissGetDialog() {
//        if (mGetDialog != null) {
//            mGetDialog.dismissAllowingStateLoss();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
//            //添加图片返回
//            if (data != null && requestCode == REQUEST_CODE_PHOTO) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                mImageItem = images.get(0);
//                ImagePicker.getInstance().getImageLoader().displayImage(this, mImageItem.path, mAvatar, 0, 0);
//                mFormAvatar = mImageItem.path;
//                uploadAvatar();
//            }
//
//        } else if (resultCode == SearchMicroActivity.RESULT_CODE_OK) {
//            if (data != null && requestCode == REQUEST_CODE_MICRODISTRICT) {
//                mSelectMico = data.getParcelableExtra(SearchMicroActivity.KEY_MICRODISTRICT);
//                mMicrodistrictText.setText(mSelectMico.getMingcheng());
//            }
//        } else {
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//        setButtonStatus();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        mNickname.removeTextChangedListener(mNicknameWathcer);
//        super.onDestroy();
//    }
}
