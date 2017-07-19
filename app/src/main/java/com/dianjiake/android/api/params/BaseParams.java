package com.dianjiake.android.api.params;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lfs on 2017/6/20.
 */

public abstract class BaseParams {
    protected Map<String, RequestBody> requestParams = new HashMap<>();

    protected RequestBody getTextBody(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    protected RequestBody getImageBody(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }

    public abstract Map<String, RequestBody> getRequestParams();

}
