package com.pca00168.eat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends Activity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.black);
        fb_login();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    //https://developers.facebook.com/apps/1612259719219926/fb-login/quickstart/
    private void fb_login(){
       // FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.button_login);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
                err(exception.getMessage());
            }
        });
    }

    private void err(String msg){
        Toast.makeText(this,msg , Toast.LENGTH_LONG).show();
    }
}