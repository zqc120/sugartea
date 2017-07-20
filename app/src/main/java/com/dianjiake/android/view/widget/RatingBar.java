package com.dianjiake.android.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dianjiake.android.R;

import java.math.BigDecimal;

/**
 * Created by hedge_hog on 2015/06/11.
 * update by hedge_hog on 2016/08/06
 */
public class RatingBar extends LinearLayout {
    private boolean mClickable;
    private boolean halfstart;
    private int totalStar;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private float starImageWidth;
    private float starImageHeight;
    private float starImagePadding;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private int y = 1;
    private boolean isEmpty = true;

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }


    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void halfStar(boolean halfstart) {
        this.halfstart = halfstart;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setStarImageWidth(float starImageWidth) {
        this.starImageWidth = starImageWidth;
    }

    public void setStarImageHeight(float starImageHeight) {
        this.starImageHeight = starImageHeight;
    }


    public void setTotalStar(int totalStar) {
        this.totalStar = totalStar;
    }

    public void setImagePadding(float starImagePadding) {
        this.starImagePadding = starImagePadding;
    }


    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);

        starHalfDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starHalf);
        starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty);
        starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starFill);
        starImageSize = mTypedArray.getDimension(R.styleable.RatingBar_starImageSize, 48);
        starImageWidth = mTypedArray.getDimension(R.styleable.RatingBar_starImageWidth, 48);
        starImageHeight = mTypedArray.getDimension(R.styleable.RatingBar_starImageHeight, 48);
        starImagePadding = mTypedArray.getDimension(R.styleable.RatingBar_starImagePadding, 4);
        totalStar = mTypedArray.getInteger(R.styleable.RatingBar_totalStar, 5);
        mClickable = mTypedArray.getBoolean(R.styleable.RatingBar_clickable, true);
        halfstart = mTypedArray.getBoolean(R.styleable.RatingBar_halfstart, false);

        for (int i = 0; i < totalStar; ++i) {
            ImageView imageView = getStarImageView(context, isEmpty, i == 0);
            imageView.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mClickable) {
                                if (halfstart) {
                                    //TODO:This is not the best way to solve half a star,
                                    //TODO:but That's what I can do,Please let me know if you have a better solution
                                    if (y % 2 == 0) {
                                        setStar(indexOfChild(v) + 1f);
                                    } else {
                                        setStar(indexOfChild(v) + 0.5f);
                                    }
                                    if (onRatingChangeListener != null) {
                                        if (y % 2 == 0) {
                                            onRatingChangeListener.onRatingChange(indexOfChild(v) + 1f);
                                            y++;
                                        } else {
                                            onRatingChangeListener.onRatingChange(indexOfChild(v) + 0.5f);
                                            y++;
                                        }
                                    }
                                } else {
                                    setStar(indexOfChild(v) + 1f);
                                    if (onRatingChangeListener != null) {
                                        onRatingChangeListener.onRatingChange(indexOfChild(v) + 1f);
                                    }
                                }

                            }

                        }
                    }
            );
            addView(imageView);
        }
    }


    private ImageView getStarImageView(Context context, boolean isEmpty, boolean isStart) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                Math.round(starImageWidth),
                Math.round(starImageHeight)
        );
        if (!isStart) {
            para.setMargins(Math.round(starImagePadding), 0, 0, 0);
        }
        imageView.setLayoutParams(para);

        if (isEmpty) {
            imageView.setImageDrawable(starEmptyDrawable);
        } else {
            imageView.setImageDrawable(starFillDrawable);
        }
        return imageView;
    }

    public void setStar(float starCount) {

        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        float fPoint = b1.subtract(b2).floatValue();


        starCount = fint > this.totalStar ? this.totalStar : fint;
        starCount = starCount < 0 ? 0 : starCount;

        //drawfullstar
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
        }

        //drawhalfstar
        if (fPoint > 0) {
            ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);
        }
        //drawemptystar
        if (starCount >= 0 && starCount <= totalStar) {
            for (int i = (int) Math.ceil(b1.floatValue()); i < totalStar; i++) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }
        }

    }

    /**
     * change start listener
     */
    public interface OnRatingChangeListener {

        void onRatingChange(float ratingCount);

    }

}