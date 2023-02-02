package com.pca00168.eat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.black);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

    }
    //https://developers.facebook.com/apps/1612259719219926/fb-login/quickstart/
    public void login_click(View view) {
       String EMAIL = "email";

        LoginButton loginButton = (LoginButton) findViewById(R.id.button_login);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(CallbackManager.Factory.create(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Home.class);
                startActivity(intent);
            }
            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException exception) {

            }
        });

    }
}