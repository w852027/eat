package com.pca00168.eat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.concurrent.TimeUnit;
public class MainActivity extends Activity {
    GoogleSignInAccount google_user;
    GoogleSignInClient mGoogleSignInClient;
    private static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/user.gender.read";
    private static final String CONTACTS_SCOPE2 = "https://www.googleapis.com/auth/user.birthday.read";
    private static final String CONTACTS_SCOPE3 = "https://www.googleapis.com/auth/fitness.activity.read";
    private static final String CONTACTS_SCOPE4 = "https://www.googleapis.com/auth/fitness.activity.write";
    private static final String CONTACTS_SCOPE5 = "https://www.googleapis.com/auth/fitness.nutrition.read";
    private static final String CONTACTS_SCOPE6 = "https://www.googleapis.com/auth/fitness.nutrition.write";
    private static final String CONTACTS_SCOPE7 = "https://www.googleapis.com/auth/fitness.sleep.read";
    private static final String CONTACTS_SCOPE8 = "https://www.googleapis.com/auth/fitness.sleep.write";
    private static final int google_fit_const = 0;
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
                .requestScopes(new Scope(CONTACTS_SCOPE3))
                .requestScopes(new Scope(CONTACTS_SCOPE4))
                .requestScopes(new Scope(CONTACTS_SCOPE5))
                .requestScopes(new Scope(CONTACTS_SCOPE6))
                .requestScopes(new Scope(CONTACTS_SCOPE7))
                .requestScopes(new Scope(CONTACTS_SCOPE8))
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
                    if (GoogleSignIn.hasPermissions(google_user,new Scope(CONTACTS_SCOPE))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE2))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE3))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE4))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE5))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE6))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE7))&&
                            GoogleSignIn.hasPermissions(google_user, new Scope(CONTACTS_SCOPE8)) ) {
                        public_func.writeData(this,"name",google_user.getDisplayName());
                        try{
                            public_func.writeData(this,"avatar",google_user.getPhotoUrl().toString());
                        }catch (NullPointerException e){
                            public_func.writeData(this,"avatar","https://cdn.cybassets.com/media/W1siZiIsIjExMTE0L3Byb2R1Y3RzLzM3NTU1MDU4L1RBNzI2NjEtMF8zYjMyZmRmNmIyMmQ3YmUzMGI5Mi5qcGVnIl0sWyJwIiwidGh1bWIiLCI0ODB4NDgwIl1d.jpeg?sha=d97d6847e727ecff");
                        }
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, google_fit_const);

/*
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACTIVITY_RECOGNITION)) {
                    //Toast.makeText(this, "被永遠拒絕，只能使用者手動給予權限", Toast.LENGTH_LONG).show();
                    //開啟應用程式資訊，讓使用者手動給予權限
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "按下拒絕", Toast.LENGTH_LONG).show();
                }
*/

            } else readFitnessData();
        startActivity(new Intent(this,Home.class));
        finish();
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case google_fit_const:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 已取得權限，在此做後續處理
                    readFitnessData();
                } else {
                    // 使用者拒絕，顯示對話框告知
                }
                break;
            default:
                break;
        }
    }

    private void readFitnessData() {
        FitnessOptions fitnessOptions = FitnessOptions.builder().addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ).build();
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    0x1001,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
            return;
        }
        long endTime = System.currentTimeMillis();
        long startTime = endTime - (24 * 60 * 60 * 1000);
        DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(readRequest)
                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
                    public void onSuccess(DataReadResponse dataReadResponse) {
                        for (DataSet dataSet : dataReadResponse.getDataSets()) {//這是一個循環，它會遍歷dataReadResult中的所有DataSet。每個DataSet是一組有關特定類型活動（例如步行或跑步）的資料。
                            for (DataPoint dp : dataSet.getDataPoints()) {//對於每一個DataSet，。每一個DataPoint代表在一個特定時間段內的資料。
                                for (Field field : dp.getDataType().getFields()) {//這個循環遍歷了DataPoint內的所有Field。Field描述了這個DataPoint內的特定類型的數據，例如步數。
                                    if (field.equals(Field.FIELD_STEPS)) {//這是一個條件判斷，用於檢查當前的Field是否是步數。
                                        int steps = dp.getValue(field).asInt();//如果上述的判斷為真，那麼這行代碼會從DataPoint中提取步數值，並將其存儲在steps變數中。
                                        // 現在，'steps' 變數包含了這個 DataPoint 的步數數據。
                                    }
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    public void onFailure(@NonNull Exception e) {
                        //err(e.getMessage());
                    }
                });
    }
    private void err(String msg){
        Toast.makeText(this,msg , Toast.LENGTH_LONG).show();
    }
}