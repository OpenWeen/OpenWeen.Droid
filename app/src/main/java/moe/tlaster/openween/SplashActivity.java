package moe.tlaster.openween;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.SettingHelper;
import moe.tlaster.openween.core.api.Entity;
import moe.tlaster.openween.core.api.user.Account;
import okhttp3.Call;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] tokens = SettingHelper.getListSetting(this, SettingHelper.ACCESSTOKEN);
        if (tokens == null) {
            goLogin();
        } else {
            Entity.setAccessToken(tokens[0]);
            try {
                Account.getUid(new JsonCallback<String>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        goHome();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        JsonParser parser = new JsonParser();
                        StaticResource.setUid(parser.parse(response).getAsJsonObject().get("uid").getAsLong());
                        navigate();
                    }
                });
            } catch (InvalidAccessTokenException e) {
                goHome();
            }
        }
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goHome() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
