package com.pca00168.eat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
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
import java.util.Collections;
public class MainActivity extends Activity {
    GoogleSignInAccount google_user;
    GoogleSignInClient mGoogleSignInClient;
    private static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/user.gender.read";
    private static final String CONTACTS_SCOPE2 = "https://www.googleapis.com/auth/user.birthday.read";
    public void google_click(View view){
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        findViewById(R.id.start_or_not).setVisibility(View.INVISIBLE);
        findViewById(R.id.google_login).setVisibility(View.INVISIBLE);
        startActivityForResult( mGoogleSignInClient.getSignInIntent(), 1);
        }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.black);


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .requestScopes(new Scope(CONTACTS_SCOPE))
                .requestScopes(new Scope(CONTACTS_SCOPE2))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        if(public_func.readData(this,"logged")!=""){
            google_click(null);
        }
    }

    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                google_user = task.getResult(ApiException.class);
                if(google_user!=null){
                    if (GoogleSignIn.hasPermissions(google_user,new Scope(CONTACTS_SCOPE))&&GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE2)) ) {
                        public_func.writeData(this,"name",google_user.getDisplayName());
                        public_func.writeData(this,"avatar",google_user.getPhotoUrl().toString());
                        public_func.writeData(this,"email",google_user.getEmail());
                        requestAccountInfo(google_user);
                    } else
                        startActivityForResult( mGoogleSignInClient.getSignInIntent(), 1);
                }
            } catch (ApiException e) {
                err(e.getMessage()+"登入失敗");
                        findViewById(R.id.progress).setVisibility(View.INVISIBLE);
                        findViewById(R.id.start_or_not).setVisibility(View.VISIBLE);
                        findViewById(R.id.google_login).setVisibility(View.VISIBLE);
            }
        }
    }
    private void requestAccountInfo(GoogleSignInAccount account) {
        new Thread(new Runnable() {
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
                } catch (IOException e) { err("生日及性別取得失敗"); }
            }
        }).start();
    }

    private void logged_in(){
        /*
        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.AGGREGATE_CALORIES_EXPENDED)
                .bucketByActivityType(1, TimeUnit.SECONDS)
                .setTimeRange(public_func.timestamp_today(), public_func.timestamp_now(), TimeUnit.SECONDS)
                .build();
        Fitness.getHistoryClient(this,google_user )
                .readData(readRequest)
                .addOnSuccessListener (response -> {
                    // The aggregate query puts datasets into buckets, so convert to a
                    // single list of datasets
                    for (Bucket bucket : response.getBuckets()) {
                        for (DataSet dataSet : bucket.getDataSets()) {
                            dumpDataSet(dataSet);
                        }
                    }
                })
                .addOnFailureListener(e ->  Log.w("TAG", "There was an error reading data from Google Fit", e));
*/
        startActivity(new Intent(this,Home.class));
        finish();
    }
/*
    private void dumpDataSet(DataSet dataSet) {
        Log.i("TAG", "Data returned for Data type: ${dataSet.dataType.name}");
        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.i("TAG","Data point:");
            Log.i("TAG","\tType: ${dp.dataType.name}");
            Log.i("TAG","\tStart: ${dp.getStartTimeString()}");
            Log.i("TAG","\tEnd: ${dp.getEndTimeString()}");
            for (Field field : dp.getDataType().getFields()) {
                Log.i("TAG","\tField: ${field.name.toString()} Value: ${dp.getValue(field)}");
            }
        }
    }*/
    private void err(String msg){
        Toast.makeText(this,msg , Toast.LENGTH_LONG).show();
    }
}