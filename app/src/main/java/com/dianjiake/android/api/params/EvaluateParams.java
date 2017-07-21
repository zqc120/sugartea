package com.dianjiake.android.api.params;

import com.dianjiake.android.constant.BSConstant;
import com.dianjiake.android.data.bean.EvaluateBean;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by lfs on 2017/7/21.
 */

public class EvaluateParams extends BaseParams {

    public EvaluateParams() {
        super();
        requestParams.put("bs", getTextBody("ceshi"));
    }

    public void setPinglunlist(String json) {
        requestParams.put("pinglunlist", getJSONBody(json));
    }

    @Override
    public Map<String, RequestBody> getRequestParams() {
        return requestParams;
    }
}
