package com.dianjiake.android.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.dianjiake.android.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lfs on 16/11/7.
 */

public abstract class BaseWebViewActivity extends BaseTranslateActivity {

    @BindView(R.id.activity_web_view)
    WebView mWebView;
    @BindView(R.id.toolbar_icon_left)
    ImageView mToolbarBack;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_icon_right)
    public ImageView mToolbarRight;
    @BindView(R.id.webview_content)
    FrameLayout mContent;

    @BindView(R.id.activity_web_view_progressbar)
    ProgressBar mProgressBar;

    @Override
    public int provideContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @JavascriptInterface
    @Override
    public void create(@Nullable Bundle savedInstanceState) {
        provideIntent(getIntent());
        addView(mContent);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http:") || url.startsWith("https:"))) {
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    view.getContext().startActivity(intent);
                } catch (Exception e) {
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(newProgress != 100 ? View.VISIBLE : View.GONE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        //解决5.0版本以后在https链接种不能正常加载图片的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        initWebView(mWebView);
        initView();
    }


    @SuppressLint("AddJavascriptInterface")
    private void initView() {
        mToolbarTitle.setText(getToolbarTitle());
        mWebView.loadUrl(getUrl());
//
    }


    @OnClick(R.id.toolbar_icon_left)
    public void click(View v) {
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

    }

    protected abstract void provideIntent(Intent intent);

    protected abstract String getToolbarTitle();

    protected abstract String getUrl();

    protected abstract void addView(FrameLayout view);

    @SuppressLint("AddJavascriptInterface")
    protected abstract void initWebView(WebView webView);

}
