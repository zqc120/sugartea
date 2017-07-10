package com.dianjiake.android.ui.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseTranslateActivity;
import com.dianjiake.android.common.FragmentFactory;
import com.dianjiake.android.util.TabFragmentManager;

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
        Fragment fm1 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
        Fragment fm2 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
        Fragment fm3 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
        Fragment fm4 = FragmentFactory.createFragmentByFM(HomeFragment.class, getFragmentManager());
//        Fragment fm3 = FragmentFactory.createFragmentByFM(MineFragment.class, getFragmentManager());
//        Fragment fmVip = new FragmentFactory().createFragmentByFM(VipListFragment.class, getFragmentManager());
//        Fragment fmPerformance = new FragmentFactory().createFragmentByFM(PerformanceFragment.class, getFragmentManager());
//
        tabManager.putFragment(R.id.main_radio_home, fm1);
        tabManager.putFragment(R.id.main_radio_home, fm2);
        tabManager.putFragment(R.id.main_radio_home, fm3);
        tabManager.putFragment(R.id.main_radio_home, fm4);
        tabManager.start();
    }


}
