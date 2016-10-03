package moe.tlaster.openween.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.helpers.SettingHelper;

/**
 * Created by Asahi on 2016/9/26.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_app_id)
    public EditText mAppId;
    @BindView(R.id.login_app_secret)
    public EditText mAppSecret;
    @BindView(R.id.login_redirect_uri)
    public EditText mRedirectUri;
    @BindView(R.id.login_scope)
    public EditText mScope;
    @BindView(R.id.login_package_name)
    public EditText mPackageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();
                if (isLoginData(str)) {
                    String[] data = decodeLoginData(s.toString());
                    if (data == null || data.length < 5) return;
                    mAppId.setText(data[0].trim());
                    mAppSecret.setText(data[1].trim());
                    mRedirectUri.setText(data[2].trim());
                    mScope.setText(data[3].trim());
                    mPackageName.setText(data[4].trim());
                }
            }
        };
        mAppId.addTextChangedListener(watcher);
        mAppSecret.addTextChangedListener(watcher);
        mRedirectUri.addTextChangedListener(watcher);
        mScope.addTextChangedListener(watcher);
        mPackageName.addTextChangedListener(watcher);
    }

    private static final String SEPERATOR = "::";
    private static final String START = "SS", END = "EE";
    private String[] decodeLoginData(String str) {
        if (!isLoginData(str))
            return null;

        String data = str.substring(START.length(), str.length() - END.length() - 1);

        try {
            return new String(Base64.decode(data, Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING | Base64.NO_CLOSE)).split(SEPERATOR);
        } catch (Exception e) {
            return null;
        }
    }
    private boolean isLoginData(String str) {
        return str.startsWith(START) && str.length() > START.length() + END.length() && str.endsWith(END);
    }


    @OnClick(R.id.login_next)
    public void login(){
        Dialog dialog = new Dialog(this, R.style.AppTheme_NoActionBar);
        dialog.setContentView(R.layout.login_web_view);
        WebView webView = (WebView)dialog.findViewById(R.id.login_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://api.weibo.com/oauth2/authorize?client_id=" + mAppId.getText() + "&response_type=token&display=mobile&redirect_uri=" +
                mRedirectUri.getText() + "&key_hash=" + mAppSecret.getText() + "&packagename=" + mPackageName.getText() + "&scope=" + mScope.getText());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!url.contains("error") && url.contains("access_token=")){
                    int tokenIndex = url.indexOf("access_token=");
                    int expiresIndex = url.indexOf("expires_in=");
                    String token = url.substring(tokenIndex + 13, url.indexOf("&", tokenIndex));
                    String expiresIn = url.substring(expiresIndex + 11, url.indexOf("&", expiresIndex));
                    SettingHelper.setListSetting(LoginActivity.this, SettingHelper.ACCESSTOKEN, token);
                    goSplash();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        dialog.setTitle("登陆");
        dialog.setCancelable(true);
    }

    private void goSplash() {
        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.login_what)
    public void what(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("data", "SSMjExMTYwNjc5OjoxZTZlMzNkYjA4ZjkxOTIzMDZjNGFmYTBhNjFhZDU2Yzo6aHR0cDovL29hdXRoLndlaWNvLmNjOjplbWFpbCxkaXJlY3RfbWVzc2FnZXNfcmVhZCxkaXJlY3RfbWVzc2FnZXNfd3JpdGUsZnJpZW5kc2hpcHNfZ3JvdXBzX3JlYWQsZnJpZW5kc2hpcHNfZ3JvdXBzX3dyaXRlLHN0YXR1c2VzX3RvX21lX3JlYWQsZm9sbG93X2FwcF9vZmZpY2lhbF9taWNyb2Jsb2csaW52aXRhdGlvbl93cml0ZTo6Y29tLmVpY28ud2VpY286OkVFEE");
        clipboard.setPrimaryClip(clip);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gist.github.com/PeterCxy/3085799055f63c63c911"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.login_cancel)
    public void cancel(){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
