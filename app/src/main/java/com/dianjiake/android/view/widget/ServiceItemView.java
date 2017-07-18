package com.dianjiake.android.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dianjiake.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sacowiw on 2017/6/15.
 */

public class ServiceItemView extends LinearLayout {

    @BindView(R.id.service_name)
    TextView serviceName;
    @BindView(R.id.service_holder)
    LinearLayout serviceHolder;

    public ServiceItemView(Context context) {
        super(context);
        init();
    }

    public ServiceItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ServiceItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        View view = inflate(getContext(), R.layout.service_item, this);
        ButterKnife.bind(this, view);
    }

    public void setServiceName(String name) {
        serviceName.setText(name);
    }
}
