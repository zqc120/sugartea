package com.dianjiake.android.ui.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.RadioGroup;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.FragmentFactory;
import com.dianjiake.android.event.LogOutEvent;
import com.dianjiake.android.event.ToOrderEvent;
import com.dianjiake.android.util.EventUtil;
import com.dianjiake.android.util.TabFragmentManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseTranslateActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.main_radio_group)
    RadioGroup mainRadioGroup;
    @BindView(R.id.main_radio_home)
    AppCompatRadioButton homeRadio;
    @BindView(R.id.main_radio_orders)
    AppCompatRadioButton orderRaido;
    TabFragmentManager tabManager;


    @Override
    public int provideContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        tabManager = new TabFragmentManager(getFragmentManager(), mainRadioGroup, true);
        initFragments();
    }


    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }


    @Override
    public void initFragments() {
        Fragment fm1 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
        Fragment fm2 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
        Fragment fm3 = FragmentFactory.createFragmentByFM(OrderFragment.class, getFragmentManager());
        Fragment fm4 = FragmentFactory.createFragmentByFM(MineFragment.class, getFragmentManager());
        tabManager.putFragment(R.id.main_radio_home, fm1);
        tabManager.putFragment(R.id.main_radio_service, fm2);
        tabManager.putFragment(R.id.main_radio_orders, fm3);
        tabManager.putFragment(R.id.main_radio_mine, fm4);
        tabManager.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LogOutEvent event) {
        homeRadio.setChecked(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeToOrder(ToOrderEvent event) {
        orderRaido.setChecked(true);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
