package com.dianjiake.android.ui.shopdetail.staff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianjiake.android.ui.common.ShopStaffAdapter;
import com.dianjiake.android.ui.shopdetail.BaseShopContentFragment;
import com.dianjiake.android.view.widget.BaseLoadMoreAdapter;

/**
 * Created by lfs on 2017/7/17.
 */

public class StaffFragment extends BaseShopContentFragment implements StaffContract.View {

    @Override
    protected StaffPresenter getPresenter() {
        return new StaffPresenter(this, shopId);
    }

    @Override
    protected BaseLoadMoreAdapter provideAdapter() {
        return new ShopStaffAdapter(presenter.getItems());
    }

    @Override
    protected void viewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}
