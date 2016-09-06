package gank.sin.me.gk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import javax.inject.Inject;

import gank.sin.me.gk.R;
import gank.sin.me.gk.common.Const;
import gank.sin.me.gk.databinding.ActivityWebActivityBinding;
import gank.sin.me.gk.ui.base.BaseActivity;
import gank.sin.me.gk.ui.viewmodel.WebViewModel;

public class WebActivity extends BaseActivity {

    @Inject WebViewModel mWebViewModel;

    private ActivityWebActivityBinding mBinding;

    public static Intent newIntent(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Const.ARG_TITLE, title);
        intent.putExtra(Const.ARG_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_activity);
        mBinding.setViewModel(mWebViewModel);

        setSupportActionBar(mBinding.toolbar);
        showBack();

        initView();
    }

    private void initView() {
        String title = getIntent().getStringExtra(Const.ARG_TITLE);
        String url = getIntent().getStringExtra(Const.ARG_URL);
        mWebViewModel.setTitle(title);
        mWebViewModel.setUrl(url);

        WebSettings settings = mBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mBinding.webView.setWebChromeClient(new ChromeClient());

        mBinding.webView.loadUrl(mWebViewModel.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_share){
            share();
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        String title = mWebViewModel.getTitle();
        String url = mWebViewModel.getUrl();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share, title, url));
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.action_share)));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mBinding.webView.canGoBack()) {
                        mBinding.webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }


}
