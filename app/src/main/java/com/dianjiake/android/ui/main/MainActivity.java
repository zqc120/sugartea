package com.dianjiake.android.ui.main;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;


import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.FragmentFactory;
import com.dianjiake.android.util.IntentUtil;
import com.dianjiake.android.util.TabFragmentManager;
import com.dianjiake.android.view.widget.ViewWrapper;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseTranslateActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.main_radio_group)
    RadioGroup mainRadioGroup;
    TabFragmentManager tabManager;

    @Override
    public int provideContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void create(@Nullable Bundle savedInstanceState) {

        tabManager = new TabFragmentManager(getFragmentManager(), mainRadioGroup);
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
//        Fragment fm1 = FragmentFactory.createFragmentByFM(SubscribeFragment.class, getFragmentManager());
//        Fragment fm3 = FragmentFactory.createFragmentByFM(MineFragment.class, getFragmentManager());
//        Fragment fmVip = new FragmentFactory().createFragmentByFM(VipListFragment.class, getFragmentManager());
//        Fragment fmPerformance = new FragmentFactory().createFragmentByFM(PerformanceFragment.class, getFragmentManager());
//
//        tabManager.putFragment(R.id.main_radio_sub, fm1);
//        tabManager.putFragment(R.id.main_radio_mine, fm3);
//        tabManager.putFragment(R.id.main_radio_vip, fmVip);
//        tabManager.putFragment(R.id.main_radio_performance, fmPerformance);
//        tabManager.start();
    }

    @OnClick(R.id.button)
    void click(View v) {
        View view = findViewById(R.id.toolbar_location_holder);

        if(view.getLayoutParams().width<1024){
            ObjectAnimator.ofInt(new ViewWrapper(view), "width", 1024).setDuration(500).start();
        }else{
            ObjectAnimator.ofInt(new ViewWrapper(view), "width", 100).setDuration(500).start();
        }
    }

}
