
package com.dianjiake.android.view.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.dianjiake.android.R;


/**
 * 可自定义比例的FrameLayout
 */
public class ProportionLayout extends FrameLayout {
    private double mWidthPro;
    private double mHeightPro;

    public ProportionLayout(Context context) {
        this(context, null);
    }

    public ProportionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProportionLayout, defStyleAttr, 0);
        mWidthPro = (double) a.getFloat(R.styleable.ProportionLayout_width_pro, 0f);
        mHeightPro = (double) a.getFloat(R.styleable.ProportionLayout_height_pro, 0f);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        if (mWidthPro <= 0 || mHeightPro <= 0) {
            heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);

        } else {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (childWidthSize * mHeightPro / mWidthPro), MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
