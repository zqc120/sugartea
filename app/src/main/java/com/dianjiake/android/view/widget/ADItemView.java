package com.dianjiake.android.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dianjiake.android.R;
import com.dianjiake.android.util.IntentUtil;


/**
 * Created by lfs on 17/3/8.
 */

public class ADItemView extends FrameLayout {
    ImageView mImage;

    public ADItemView(Context context) {
        this(context, null);
    }

    public ADItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ADItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.ad_item_view, this);
        mImage = (ImageView) view.findViewById(R.id.ad_item_view_image);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static ADItemView newInstance(Context context) {
        ADItemView view = new ADItemView(context);
        return view;
    }

    public void load() {

    }


}
