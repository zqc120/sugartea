package com.dianjiake.android.ui.common;

import android.content.Context;

import com.dianjiake.android.base.BaseListView;

/**
 * Created by lfs on 2017/7/20.
 */

public interface OrderView extends BaseListView {
    void setShowNoCommentHolder(boolean show);

    boolean getShowNoCommentHolder();

    void setNoCommentCount(int count);

    Context getViewContext();
}
