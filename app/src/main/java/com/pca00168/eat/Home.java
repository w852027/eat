package com.pca00168.eat;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.pca00168.eat.databinding.HomeBinding;
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
    }
    public void settings_click(View view) {
        Intent intent = new Intent();
        intent.setClass(Home.this, Mine.class);
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
    public void animal_onClick(View view) {
        Intent intent = new Intent(this, My_Closet.class);
        startActivity(intent);
    }
}