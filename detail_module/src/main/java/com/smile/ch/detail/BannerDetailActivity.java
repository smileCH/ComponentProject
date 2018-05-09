package com.smile.ch.detail;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smile.ch.common.base.BaseMvpActivity;
import com.smile.ch.common.base.BasePresenter;
import com.smile.ch.detail.constants.Constant;

@Route(path = "/detail/banner")
public class BannerDetailActivity extends BaseMvpActivity {

    private WebView webView;
    private String bannerUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initIntent() {
        bannerUrl = getIntent().getStringExtra(Constant.BANNER_URL_INTENT);
    }

    @Override
    protected void findViews() {
        webView = findViewById(R.id.detail_banner_wv);
    }

    @Override
    protected void setViews() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        //自适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        //支持缩放
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);//隐藏原生的缩放控件
        webView.loadUrl(bannerUrl);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void getData() {

    }
}
