package com.pca00168.eat;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.pca00168.eat.databinding.HomeBinding;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import java.util.concurrent.TimeUnit;
public class Home extends AppCompatActivity {
    private HomeBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_green);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.ACTIVITY_RECOGNITION,
                    new CheckRequestPermissionListener() {
                        public void onPermissionOk(Permission permission) {
                            Toast.makeText(Home.this,"grant", Toast.LENGTH_SHORT).show();
                        }
                        public void onPermissionDenied(Permission permission) {
                            Toast.makeText(Home.this,"deny", Toast.LENGTH_SHORT).show();
                        }
                    });
    }
    public void settings_click(View view) {
        Intent intent = new Intent();
        intent.setClass(Home.this, Mine.class);
        startActivity(intent);
    }
    public void shop_click(View view) {
        Intent intent = new Intent();
        intent.setClass(Home.this, shop.class);
        startActivity(intent);
    }
    public void weekly_report_click(View view) {
        Intent intent = new Intent();
        intent.setClass(Home.this, weekly_report.class);
        startActivity(intent);
    }
    public void input_today_detial_click(View view) {
        Intent intent = new Intent(Home.this, today_detail.class);
        intent.putExtra("request_input",true);
        startActivity(intent);
    }
    public void output_today_detial_click(View view) {
        Intent intent = new Intent(Home.this, today_detail.class);
        intent.putExtra("request_input",false);
        startActivity(intent);
    }
    public void branch_onClick(View v){
        startActivity(new Intent(this,shop_branch.class));
    }
    public void animal_onClick(View view) {
        Intent intent = new Intent(this, My_Closet.class);
        startActivity(intent);
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
}