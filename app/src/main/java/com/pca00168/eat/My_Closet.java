package com.pca00168.eat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
public class My_Closet extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.my_closet);
    }
}