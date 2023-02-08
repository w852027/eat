package com.pca00168.eat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Birthday;
import com.google.api.services.people.v1.model.Gender;
import com.google.api.services.people.v1.model.Person;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.black);
        logged_in();
        return;
        //google_login();
        //fb_login();
    }
    private static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/user.gender.read";
    private static final String CONTACTS_SCOPE2 = "https://www.googleapis.com/auth/user.birthday.read";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                Scope scope1= new Scope(CONTACTS_SCOPE);
                Scope scope2=new Scope(CONTACTS_SCOPE2);

                if(account!=null){
                    if (GoogleSignIn.hasPermissions(account,scope1)&&GoogleSignIn.hasPermissions(account, scope2) ) {
                        public_func.writeData(this,"name",account.getDisplayName());
                        public_func.writeData(this,"avatar",account.getPhotoUrl().toString());
                        public_func.writeData(this,"email",account.getEmail());
                        requestAccountInfo(account);
                    } else{
                        startActivityForResult( mGoogleSignInClient.getSignInIntent(), 1);
                        //GoogleSignIn.requestPermissions(MainActivity.this, 2002, account, scope1);
                        //GoogleSignIn.requestPermissions(MainActivity.this, 2002, account, scope2);
                    }
                }
            } catch (ApiException e) {
                err(e.getMessage()+"登入失敗");
            }

        }else
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void requestAccountInfo(GoogleSignInAccount account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GoogleAccountCredential googleAccountCredential = GoogleAccountCredential.usingOAuth2(MainActivity.this, Collections.singleton(CONTACTS_SCOPE)).setSelectedAccount(account.getAccount());
                    PeopleService peopleService = new PeopleService.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), googleAccountCredential).build();
                    Person person = peopleService.people()
                            .get("people/me")
                            .setRequestMaskIncludeField("person.genders")
                            .setFields("genders")
                            .execute();
                    for (Gender gender : person.getGenders()) {
                        public_func.writeData(getBaseContext(),"sex",gender.getValue());
                    }

                     person = peopleService.people()
                            .get("people/me")
                            .setRequestMaskIncludeField("person.birthdays")
                            .setFields("birthdays")
                            .execute();
                    for (Birthday day : person.getBirthdays()) {
                        public_func.writeData(getBaseContext(),"birthday",
                               String.format ("%d/%d/%d",day.getDate().getYear(),day.getDate().getMonth(),day.getDate().getDay())
                        );
                    }
                    public_func.writeData(getBaseContext(),"logged","google");
                    logged_in();
                } catch (IOException e) {
                            err("生日及性別取得失敗");
                }
            }
        }).start();
    }

    private void fb_login(){
       // FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.fb_login);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                logged_in();
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

    private void  google_login(){
        if(public_func.readData(this,"logged")!=""){
            logged_in();
            return;
        }
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .requestScopes(new Scope(CONTACTS_SCOPE))
                .requestScopes(new Scope(CONTACTS_SCOPE2))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void logged_in(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Home.class);
        startActivity(intent);
    }
    public void google_click(View view){
        startActivityForResult( mGoogleSignInClient.getSignInIntent(), 1);
    }
    private void err(String msg){
        Toast.makeText(this,msg , Toast.LENGTH_LONG).show();
    }
}