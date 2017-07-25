package com.dianjiake.android.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.dianjiake.android.R;

/**
 * Created by lfs on 2017/7/20.
 */

public class TableRowUtil {

    public static TextView getServiceTitleText(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
        textView.setTextColor(UIUtil.getColor(R.color.text_content_title));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setText(text);
        return textView;
    }

    public static TextView getServiceCenterText(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        textView.setPadding((int) UIUtil.dp2px(8), 0, 0, 0);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(UIUtil.getColor(R.color.text_content_title));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setText(text);
        return textView;
    }

    public static TextView getServiceEndText(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        textView.setPadding((int) UIUtil.dp2px(8), 0, 0, 0);
        textView.setTextColor(UIUtil.getColor(R.color.text_content_title));
        textView.setGravity(Gravity.END);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setText(text);
        return textView;
    }

    public static TextView getCountTitleText(String text, Context context) {
        TextView tv = getServiceTitleText(text, context);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        tv.setTextColor(UIUtil.getColor(R.color.text_content_secondary));
        return tv;
    }

    public static TextView getCountEndText(String text, Context context) {
        TextView tv = getServiceEndText(text, context);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        tv.setTextColor(UIUtil.getColor(R.color.text_content_secondary));
        return tv;
    }

    public static TextView getVipTitleText(String text, Context context) {
        return getServiceTitleText(text, context);
    }

    public static TextView getVipEndText(String text, Context context) {
        TextView tv = getServiceEndText(text, context);
        tv.setTextColor(UIUtil.getColor(R.color.main));
        return tv;
    }


    public static TextView getSubText(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setTextColor(UIUtil.getColor(R.color.text_content_title));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setText(text);
        return textView;
    }

    public static TextView getVipTitle(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
        textView.setTextColor(UIUtil.getColor(R.color.text_white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        textView.setText(text);
        return textView;
    }

    public static TextView getVipTitleEnd(String text, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        textView.setPadding((int) UIUtil.dp2px(8), 0, 0, 0);
        textView.setTextColor(UIUtil.getColor(R.color.text_white));
        textView.setGravity(Gravity.END);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        textView.setText(text);
        return textView;
    }
}
