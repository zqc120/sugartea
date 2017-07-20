package com.dianjiake.android.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dianjiake.android.R;
import com.dianjiake.android.api.Network;
import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.ADItemBean;
import com.dianjiake.android.data.db.AppInfoDBHelper;
import com.dianjiake.android.data.model.AppInfoModel;
import com.dianjiake.android.util.FrescoUtil;
import com.dianjiake.android.util.IntentUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lfs on 17/3/8.
 */

public class ADItemView extends FrameLayout {
    SimpleDraweeView mImage;

    public ADItemView(Context context) {
        this(context, null);
    }

    public ADItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ADItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.ad_item_view, this);
        mImage = (SimpleDraweeView) view.findViewById(R.id.ad_item_view_image);
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

    public void load(ADItemBean adItemBean) {
        mImage.setImageURI(FrescoUtil.getADUri(adItemBean.getPic()));
    }


}
